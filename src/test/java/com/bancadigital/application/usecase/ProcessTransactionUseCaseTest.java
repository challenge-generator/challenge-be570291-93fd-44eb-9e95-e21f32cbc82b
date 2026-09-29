package com.bancadigital.application.usecase;

import com.bancadigital.domain.model.Transaction;
import com.bancadigital.domain.model.Transaction.TransactionStatus;
import com.bancadigital.domain.port.TransactionRepository;
import com.bancadigital.domain.port.AccountSystemClient;
import com.bancadigital.domain.exception.IdempotencyConflictException;
import com.bancadigital.domain.exception.AccountNotFoundException;
import com.bancadigital.domain.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessTransactionUseCase - Pruebas de unidad")
class ProcessTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountSystemClient accountSystemClient;

    private ProcessTransactionUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProcessTransactionUseCase(transactionRepository, accountSystemClient);
    }

    @Nested
    @DisplayName("Escenarios de idempotencia")
    class IdempotencyScenarios {

        @Test
        @DisplayName("Primera invocación con clave de idempotencia - crea nueva transacción")
        void firstInvocationWithIdempotencyKey_createsNewTransaction() {
            String idempotencyKey = "OP001-CHANNEL_WEB";
            Transaction transaction = Transaction.createPendingTransaction(
                "OP001", "WEB", new BigDecimal("1000.00"), "1234567890", "0987654321"
            );
            transaction.setIdempotencyKey(idempotencyKey);

            when(transactionRepository.existsByIdempotencyKey(idempotencyKey)).thenReturn(Mono.just(false));
            when(accountSystemClient.validateAccount("1234567890")).thenReturn(Mono.just(true));
            when(accountSystemClient.validateAccount("0987654321")).thenReturn(Mono.just(true));
            when(accountSystemClient.checkSufficientFunds("1234567890", new BigDecimal("1000.00")))
                .thenReturn(Mono.just(true));
            when(accountSystemClient.transferFunds("1234567890", "0987654321", new BigDecimal("1000.00"), "OP001"))
                .thenReturn(Mono.just(true));
            when(transactionRepository.save(any(Transaction.class))).thenReturn(Mono.just(transaction));

            StepVerifier.create(useCase.execute("OP001", "WEB", new BigDecimal("1000.00"), "1234567890", "0987654321", idempotencyKey))
                .expectNextMatches(tx -> tx.status() == TransactionStatus.COMPLETED)
                .verifyComplete();

            verify(transactionRepository).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Segunda invocación con misma clave de idempotencia - retorna transacción existente")
        void secondInvocationWithSameIdempotencyKey_returnsExistingTransaction() {
            String idempotencyKey = "OP001-CHANNEL_WEB";
            Transaction existingTransaction = Transaction.createPendingTransaction(
                "OP001", "WEB", new BigDecimal("1000.00"), "1234567890", "0987654321"
            );
            existingTransaction.setIdempotencyKey(idempotencyKey);
            existingTransaction.complete();

            when(transactionRepository.existsByIdempotencyKey(idempotencyKey)).thenReturn(Mono.just(true));
            when(transactionRepository.findByIdempotencyKey(idempotencyKey))
                .thenReturn(Mono.just(existingTransaction));

            StepVerifier.create(useCase.execute("OP001", "WEB", new BigDecimal("1000.00"), "1234567890", "0987654321", idempotencyKey))
                .expectNextMatches(tx -> tx.status() == TransactionStatus.COMPLETED && tx.transactionId().equals(existingTransaction.transactionId()))
                .verifyComplete();

            verify(transactionRepository, never()).save(any(Transaction.class));
            verify(accountSystemClient, never()).transferFunds(anyString(), anyString(), any(), anyString());
        }

        @Test
        @DisplayName("Clave de idempotencia duplicada con parámetros diferentes - lanza excepción")
        void duplicateIdempotencyKeyWithDifferentParameters_throwsException() {
            String idempotencyKey = "OP001-CHANNEL_WEB";
            Transaction existingTransaction = Transaction.createPendingTransaction(
                "OP001", "WEB", new BigDecimal("1000.00"), "1234567890", "0987654321"
            );
            existingTransaction.setIdempotencyKey(idempotencyKey);

            when(transactionRepository.existsByIdempotencyKey(idempotencyKey)).thenReturn(Mono.just(true));
            when(transactionRepository.findByIdempotencyKey(idempotencyKey))
                .thenReturn(Mono.just(existingTransaction));

            StepVerifier.create(useCase.execute("OP001", "WEB", new BigDecimal("2000.00"), "1111111111", "2222222222", idempotencyKey))
                .expectError(IdempotencyConflictException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de validación de cuenta")
    class AccountValidationScenarios {

        @Test
        @DisplayName("Cuenta de origen inexistente - lanza AccountNotFoundException")
        void nonExistentSourceAccount_throwsAccountNotFoundException() {
            String idempotencyKey = "OP002-CHANNEL_MOBILE";

            when(transactionRepository.existsByIdempotencyKey(idempotencyKey)).thenReturn(Mono.just(false));
            when(accountSystemClient.validateAccount("0000000000")).thenReturn(Mono.just(false));

            StepVerifier.create(useCase.execute("OP002", "MOBILE", new BigDecimal("500.00"), "0000000000", "0987654321", idempotencyKey))
                .expectError(AccountNotFoundException.class)
                .verify();
        }

        @Test
        @DisplayName("Fondos insuficientes - lanza InsufficientFundsException")
        void insufficientFunds_throwsInsufficientFundsException() {
            String idempotencyKey = "OP003-CHANNEL_ATM";

            when(transactionRepository.existsByIdempotencyKey(idempotencyKey)).thenReturn(Mono.just(false));
            when(accountSystemClient.validateAccount("1234567890")).thenReturn(Mono.just(true));
            when(accountSystemClient.validateAccount("0987654321")).thenReturn(Mono.just(true));
            when(accountSystemClient.checkSufficientFunds("1234567890", new BigDecimal("999999.99")))
                .thenReturn(Mono.just(false));

            StepVerifier.create(useCase.execute("OP003", "ATM", new BigDecimal("999999.99"), "1234567890", "0987654321", idempotencyKey))
                .expectError(InsufficientFundsException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de timeout")
    class TimeoutScenarios {

        @Test
        @DisplayName("Timeout en validación de cuenta - la transacción queda en estado PENDING")
        void timeoutInAccountValidation_transactionRemainsPending() {
            String idempotencyKey = "OP004-CHANNEL_API";
            Transaction pendingTransaction = Transaction.createPendingTransaction(
                "OP004", "API", new BigDecimal("2500.00"), "1234567890", "0987654321"
            );
            pendingTransaction.setIdempotencyKey(idempotencyKey);

            when(transactionRepository.existsByIdempotencyKey(idempotencyKey)).thenReturn(Mono.just(false));
            when(accountSystemClient.validateAccount("1234567890"))
                .thenReturn(Mono.error(new RuntimeException("Connection timeout")));
            when(transactionRepository.save(any(Transaction.class))).thenReturn(Mono.just(pendingTransaction));

            StepVerifier.create(useCase.execute("OP004", "API", new BigDecimal("2500.00"), "1234567890", "0987654321", idempotencyKey))
                .expectNextMatches(tx -> tx.status() == TransactionStatus.PENDING)
                .verifyComplete();

            verify(transactionRepository).save(any(Transaction.class));
        }
    }
}