package com.bancadigital.domain.port;

import reactor.core.publisher.Mono;
import java.math.BigDecimal;

public interface AccountSystemClient {
    Mono<Boolean> validateAccount(String accountNumber);
    Mono<Boolean> checkSufficientFunds(String accountNumber, BigDecimal amount);
    Mono<Boolean> transferFunds(String fromAccount, String toAccount, BigDecimal amount, String operationNumber);
}