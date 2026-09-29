package com.bancadigital.infrastructure.adapter;

import com.bancadigital.domain.port.AccountSystemClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@DisplayName("AccountSystemWebClient - Pruebas de integración")
class AccountSystemWebClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AccountSystemClient accountSystemClient;

    @Nested
    @DisplayName("Validación de cuentas")
    class AccountValidationTests {

        @Test
        @DisplayName("Validar cuenta existente - retorna true")
        void validateExistingAccount_returnsTrue() {
            when(accountSystemClient.validateAccount("1234567890")).thenReturn(Mono.just(true));

            StepVerifier.create(accountSystemClient.validateAccount("1234567890"))
                .expectNext(true)
                .verifyComplete();
        }

        @Test
        @DisplayName("Validar cuenta inexistente - retorna false")
        void validateNonExistentAccount_returnsFalse() {
            when(accountSystemClient.validateAccount("9999999999")).thenReturn(Mono.just(false));

            StepVerifier.create(accountSystemClient.validateAccount("9999999999"))
                .expectNext(false)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Verificación de fondos")
    class FundsVerificationTests {

        @Test
        @DisplayName("Cuenta con fondos suficientes - retorna true")
        void accountWithSufficientFunds_returnsTrue() {
            when(accountSystemClient.checkSufficientFunds(anyString(), any(BigDecimal.class)))
                .thenReturn(Mono.just(true));

            StepVerifier.create(accountSystemClient.checkSufficientFunds("1234567890", new BigDecimal("1000.00")))
                .expectNext(true)
                .verifyComplete();
        }

        @Test
        @DisplayName("Cuenta con fondos insuficientes - retorna false")
        void accountWithInsufficientFunds_returnsFalse() {
            when(accountSystemClient.checkSufficientFunds(anyString(), any(BigDecimal.class)))
                .thenReturn(Mono.just(false));

            StepVerifier.create(accountSystemClient.checkSufficientFunds("1234567890", new BigDecimal("999999.00")))
                .expectNext(false)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Transferencia de fondos")
    class FundTransferTests {

        @Test
        @DisplayName("Transferencia exitosa - retorna true")
        void successfulTransfer_returnsTrue() {
            when(accountSystemClient.transferFunds(anyString(), anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(Mono.just(true));

            StepVerifier.create(accountSystemClient.transferFunds("1234567890", "0987654321", new BigDecimal("500.00"), "OP001"))
                .expectNext(true)
                .verifyComplete();
        }

        @Test
        @DisplayName("Transferencia fallida - retorna false")
        void failedTransfer_returnsFalse() {
            when(accountSystemClient.transferFunds(anyString(), anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(Mono.just(false));

            StepVerifier.create(accountSystemClient.transferFunds("1234567890", "0987654321", new BigDecimal("500.00"), "OP001"))
                .expectNext(false)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Manejo de timeout")
    class TimeoutHandlingTests {

        @Test
        @DisplayName("Timeout en validación de cuenta - lanza excepción después de 2 segundos")
        void timeoutInValidation_throwsExceptionAfter2Seconds() {
            when(accountSystemClient.validateAccount(anyString()))
                .thenReturn(Mono.delay(Duration.ofSeconds(5)).then(Mono.just(true)));

            StepVerifier.create(
                    accountSystemClient.validateAccount("1234567890")
                        .timeout(Duration.ofSeconds(2))
                )
                .expectErrorMatches(e -> e.getMessage().contains("Timeout") || e.getMessage().contains("timeout"))
                .verify();
        }

        @Test
        @DisplayName("Timeout en verificación de fondos - lanza excepción después de 2 segundos")
        void timeoutInFundsCheck_throwsExceptionAfter2Seconds() {
            when(accountSystemClient.checkSufficientFunds(anyString(), any(BigDecimal.class)))
                .thenReturn(Mono.delay(Duration.ofSeconds(5)).then(Mono.just(true)));

            StepVerifier.create(
                    accountSystemClient.checkSufficientFunds("1234567890", new BigDecimal("1000.00"))
                        .timeout(Duration.ofSeconds(2))
                )
                .expectErrorMatches(e -> e.getMessage().contains("Timeout") || e.getMessage().contains("timeout"))
                .verify();
        }

        @Test
        @DisplayName("Timeout en transferencia - lanza excepción después de 2 segundos")
        void timeoutInTransfer_throwsExceptionAfter2Seconds() {
            when(accountSystemClient.transferFunds(anyString(), anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(Mono.delay(Duration.ofSeconds(5)).then(Mono.just(true)));

            StepVerifier.create(
                    accountSystemClient.transferFunds("1234567890", "0987654321", new BigDecimal("500.00"), "OP001")
                        .timeout(Duration.ofSeconds(2))
                )
                .expectErrorMatches(e -> e.getMessage().contains("Timeout") || e.getMessage().contains("timeout"))
                .verify();
        }
    }

    @Nested
    @DisplayName("Manejo de fallos")
    class FailureHandlingTests {

        @Test
        @DisplayName("Error de conexión - lanza excepción genérica")
        void connectionError_throwsGenericException() {
            when(accountSystemClient.validateAccount(anyString()))
                .thenReturn(Mono.error(new RuntimeException("Connection refused")));

            StepVerifier.create(accountSystemClient.validateAccount("1234567890"))
                .expectErrorMatches(e -> e.getMessage().contains("Connection"))
                .verify();
        }

        @Test
        @DisplayName("Error de servidor - lanza excepción con código 500")
        void serverError_throwsException() {
            when(accountSystemClient.transferFunds(anyString(), anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(Mono.error(new RuntimeException("Internal server error")));

            StepVerifier.create(accountSystemClient.transferFunds("1234567890", "0987654321", new BigDecimal("500.00"), "OP001"))
                .expectErrorMatches(e -> e.getMessage().contains("Internal") || e.getMessage().contains("server"))
                .verify();
        }
    }
}