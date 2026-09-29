package com.bancadigital.infrastructure.adapter;

import com.bancadigital.domain.port.AccountSystemClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.time.Duration;

@Component
public class AccountSystemWebClient implements AccountSystemClient {

    private static final Logger log = LoggerFactory.getLogger(AccountSystemWebClient.class);

    private final WebClient webClient;
    private final Duration connectionTimeout;
    private final Duration responseTimeout;

    public AccountSystemWebClient(
            @Value("${account-system.base-url:http://localhost:8081}") String baseUrl,
            @Value("${account-system.connection-timeout-ms:5000}") int connectionTimeoutMs,
            @Value("${account-system.response-timeout-ms:2000}") int responseTimeoutMs) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
        this.connectionTimeout = Duration.ofMillis(connectionTimeoutMs);
        this.responseTimeout = Duration.ofMillis(responseTimeoutMs);
    }

    @Override
    public Mono<Boolean> validateAccount(String accountNumber) {
        log.debug("Validando cuenta: {}", accountNumber);
        return webClient.get()
                .uri("/api/accounts/{accountNumber}/validate", accountNumber)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.warn("Cuenta no encontrada: {}", accountNumber);
                    return Mono.just(new AccountNotFoundException("Cuenta no encontrada: " + accountNumber));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Error del servidor al validar cuenta: {}", accountNumber);
                    return Mono.just(new AccountSystemException("Error interno del sistema de cuentas"));
                })
                .bodyToMono(AccountValidationResponse.class)
                .timeout(connectionTimeout)
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500))
                        .filter(throwable -> !(throwable instanceof AccountNotFoundException)))
                .map(AccountValidationResponse::isValid)
                .doOnSuccess(result -> log.debug("Validación de cuenta {} completada: {}", accountNumber, result))
                .doOnError(error -> log.error("Error validando cuenta {}: {}", accountNumber, error.getMessage()));
    }

    @Override
    public Mono<Boolean> checkSufficientFunds(String accountNumber, BigDecimal amount) {
        log.debug("Verificando fondos para cuenta: {}, monto: {}", accountNumber, amount);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/accounts/{accountNumber}/funds")
                        .queryParam("amount", amount.toString())
                        .build(accountNumber))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.warn("Fondos insuficientes para cuenta: {}", accountNumber);
                    return Mono.just(new InsufficientFundsException("Fondos insuficientes en cuenta: " + accountNumber));
                })
                .bodyToMono(FundsCheckResponse.class)
                .timeout(responseTimeout)
                .retryWhen(Retry.backoff(2, Duration.ofMillis(300))
                        .filter(throwable -> !(throwable instanceof InsufficientFundsException)))
                .map(FundsCheckResponse::isSufficient)
                .doOnSuccess(result -> log.debug("Verificación de fondos para {} completada: {}", accountNumber, result))
                .doOnError(error -> log.error("Error verificando fondos para {}: {}", accountNumber, error.getMessage()));
    }

    @Override
    public Mono<Boolean> transferFunds(String fromAccount, String toAccount, BigDecimal amount,
                                        String operationNumber) {
        log.info("Iniciando transferencia: {} -> {}, monto: {}, operación: {}",
                fromAccount, toAccount, amount, operationNumber);

        TransferRequest request = new TransferRequest(fromAccount, toAccount, amount, operationNumber);

        return webClient.post()
                .uri("/api/accounts/transfer")
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.error("Error de cliente en transferencia: {}", response.statusCode());
                    return Mono.just(new TransferFailedException("Error en la solicitud de transferencia"));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Error de servidor en transferencia: {}", response.statusCode());
                    return Mono.just(new AccountSystemException("Error del sistema de cuentas durante transferencia"));
                })
                .bodyToMono(TransferResponse.class)
                .timeout(responseTimeout.plus(Duration.ofSeconds(1)))
                .retryWhen(Retry.backoff(2, Duration.ofMillis(500)))
                .map(TransferResponse::isSuccess)
                .doOnSuccess(result -> log.info("Transferencia {} -> {} completada: {}",
                        fromAccount, toAccount, result))
                .doOnError(error -> log.error("Error en transferencia {} -> {}: {}",
                        fromAccount, toAccount, error.getMessage()));
    }

    private static class AccountValidationResponse {
        private boolean valid;

        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
    }

    private static class FundsCheckResponse {
        private boolean sufficient;

        public boolean isSufficient() { return sufficient; }
        public void setSufficient(boolean sufficient) { this.sufficient = sufficient; }
    }

    private static class TransferRequest {
        private String fromAccount;
        private String toAccount;
        private BigDecimal amount;
        private String operationNumber;

        public TransferRequest(String fromAccount, String toAccount, BigDecimal amount, String operationNumber) {
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
            this.amount = amount;
            this.operationNumber = operationNumber;
        }

        public String getFromAccount() { return fromAccount; }
        public String getToAccount() { return toAccount; }
        public BigDecimal getAmount() { return amount; }
        public String getOperationNumber() { return operationNumber; }
    }

    private static class TransferResponse {
        private boolean success;

        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
    }

    private static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String message) { super(message); }
    }

    private static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) { super(message); }
    }

    private static class TransferFailedException extends RuntimeException {
        public TransferFailedException(String message) { super(message); }
    }

    private static class AccountSystemException extends RuntimeException {
        public AccountSystemException(String message) { super(message); }
    }
}