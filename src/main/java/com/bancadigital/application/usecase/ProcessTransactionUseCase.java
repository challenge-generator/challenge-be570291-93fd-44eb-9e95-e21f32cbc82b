package com.bancadigital.application.usecase;

import com.bancadigital.domain.model.Transaction;
import com.bancadigital.domain.model.Transaction.TransactionStatus;
import com.bancadigital.domain.port.AccountSystemClient;
import com.bancadigital.domain.port.TransactionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProcessTransactionUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessTransactionUseCase.class);
    private static final int IDEMPOTENCY_WINDOW_HOURS = 24;

    private final TransactionRepository transactionRepository;
    private final AccountSystemClient accountSystemClient;
    private final Duration operationTimeout;

    public ProcessTransactionUseCase(
            TransactionRepository transactionRepository,
            AccountSystemClient accountSystemClient,
            @Value("${transaction.timeout.seconds:2}") int timeoutSeconds) {
        this.transactionRepository = transactionRepository;
        this.accountSystemClient = accountSystemClient;
        this.operationTimeout = Duration.ofSeconds(timeoutSeconds);
    }

    @CircuitBreaker(name = "accountSystem", fallbackMethod = "handleAccountSystemFallback")
    @TimeLimiter(name = "accountSystem", fallbackMethod = "handleTimeoutFallback")
    @Retry(name = "accountSystem")
    public Mono<Transaction> execute(String operationNumber, String channel, String accountFrom,
                                      String accountTo, BigDecimal amount) {
        String idempotencyKey = buildIdempotencyKey(operationNumber, channel);

        return transactionRepository.findByIdempotencyKey(idempotencyKey)
                .flatMap(existingTransaction -> {
                    log.info("Transacción idempotente encontrada para clave: {}", idempotencyKey);
                    return Mono.just(existingTransaction);
                })
                .switchIfEmpty(createAndProcessTransaction(operationNumber, channel, accountFrom,
                        accountTo, amount, idempotencyKey))
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Transaction> createAndProcessTransaction(String operationNumber, String channel,
                                                           String accountFrom, String accountTo,
                                                           BigDecimal amount, String idempotencyKey) {
        log.info("Creando nueva transacción con clave de idempotencia: {}", idempotencyKey);

        Transaction pendingTransaction = Transaction.builder()
                .transactionId(UUID.randomUUID())
                .operationNumber(operationNumber)
                .channel(channel)
                .amount(amount)
                .status(TransactionStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .idempotencyKey(idempotencyKey)
                .build();

        return transactionRepository.save(pendingTransaction)
                .flatMap(savedTransaction -> validateAndExecuteTransfer(savedTransaction))
                .onErrorResume(error -> {
                    log.error("Error al procesar transacción: {}", error.getMessage());
                    return Mono.error(error);
                });
    }

    private Mono<Transaction> validateAndExecuteTransfer(Transaction transaction) {
        return accountSystemClient.validateAccount(transaction.getAccountFrom())
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Cuenta de origen inválida")))
                .then(accountSystemClient.validateAccount(transaction.getAccountTo()))
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Cuenta de destino inválida")))
                .then(accountSystemClient.checkSufficientFunds(transaction.getAccountFrom(),
                        transaction.getAmount()))
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Fondos insuficientes")))
                .then(accountSystemClient.transferFunds(transaction.getAccountFrom(),
                        transaction.getAccountTo(), transaction.getAmount(),
                        transaction.getOperationNumber()))
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new IllegalStateException("Falló la transferencia en sistema de cuentas")))
                .then(updateTransactionStatus(transaction, TransactionStatus.COMPLETED))
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Transaction> updateTransactionStatus(Transaction transaction, TransactionStatus status) {
        Transaction updatedTransaction = Transaction.builder()
                .transactionId(transaction.getTransactionId())
                .operationNumber(transaction.getOperationNumber())
                .channel(transaction.getChannel())
                .amount(transaction.getAmount())
                .status(status)
                .createdAt(transaction.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .accountFrom(transaction.getAccountFrom())
                .accountTo(transaction.getAccountTo())
                .idempotencyKey(transaction.getIdempotencyKey())
                .build();

        return transactionRepository.save(updatedTransaction);
    }

    private String buildIdempotencyKey(String operationNumber, String channel) {
        return channel + "_" + operationNumber;
    }

    private Mono<Transaction> handleAccountSystemFallback(String operationNumber, String channel,
                                                           String accountFrom, String accountTo,
                                                           BigDecimal amount, Throwable throwable) {
        log.warn("Circuit breaker activado para sistema de cuentas. Operación: {}, Error: {}",
                operationNumber, throwable.getMessage());
        return Mono.error(new RuntimeException("Sistema de cuentas no disponible temporalmente."));
    }

    private Mono<Transaction> handleTimeoutFallback(String operationNumber, String channel,
                                                     String accountFrom, String accountTo,
                                                     BigDecimal amount, Throwable throwable) {
        log.error("Timeout en operación de cuenta. Operación: {}, Tiempo límite: {}",
                operationNumber, operationTimeout);
        return Mono.error(new RuntimeException("Tiempo de espera agotado para el sistema de cuentas."));
    }
}