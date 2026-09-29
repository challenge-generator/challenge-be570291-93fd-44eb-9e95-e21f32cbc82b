# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Integración y Configuración de Spring Boot en un Proyecto Backend**.

| | |
|---|---|
| Tema | Sólida Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas reactivas para alta demanda |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-data-r2dbc 3.5.6
- io.r2dbc:r2dbc-postgresql 1.0.5.RELEASE
- org.springframework.boot:spring-boot-starter-aop n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.springframework.boot:spring-boot-starter-cache n/a
- org.springframework.boot:spring-boot-starter-data-redis-reactive n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springdoc:springdoc-openapi-starter-webflux-ui 2.5.0
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- io.projectreactor:reactor-test n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración Inicial de Spring Boot**: Proyecto de Spring Boot configurado con las especificaciones iniciales.
- **Fase 2 — Integración con el Sistema de Cuentas**: Proyecto de Spring Boot integrado con el sistema de cuentas.
- **Fase 3 — Optimización y Escalabilidad**: Proyecto de Spring Boot optimizado y escalable.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/bancadigital/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (84)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.IdempotencyConflictException`
      El import com.bancadigital.domain.exception.IdempotencyConflictException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.AccountNotFoundException`
      El import com.bancadigital.domain.exception.AccountNotFoundException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.InsufficientFundsException`
      El import com.bancadigital.domain.exception.InsufficientFundsException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.AccountSystemTimeoutException`
      El import com.bancadigital.domain.exception.AccountSystemTimeoutException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.TransactionProcessingException`
      El import com.bancadigital.domain.exception.TransactionProcessingException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `com.bancadigital.domain.exception.IdempotencyConflictException`
      El import com.bancadigital.domain.exception.IdempotencyConflictException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `com.bancadigital.domain.exception.AccountNotFoundException`
      El import com.bancadigital.domain.exception.AccountNotFoundException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `com.bancadigital.domain.exception.InsufficientFundsException`
      El import com.bancadigital.domain.exception.InsufficientFundsException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bancadigital/Application.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/Application.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/domain/port/TransactionRepository.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/domain/port/AccountSystemClient.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java` — `reactor.util.retry`
      El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/config/IdempotencyConfig.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClientTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bancadigital/Application.java` — `ProcessTransactionUseCase.processTransaction`
      Se invoca `processTransaction` sobre `ProcessTransactionUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setTransactionId`
      Se invoca `setTransactionId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getTransactionId`
      Se invoca `getTransactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setOperationNumber`
      Se invoca `setOperationNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getOperationNumber`
      Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setChannel`
      Se invoca `setChannel` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getChannel`
      Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setAmount`
      Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getAmount`
      Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setStatus`
      Se invoca `setStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getStatus`
      Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setCreatedAt`
      Se invoca `setCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getCreatedAt`
      Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getUpdatedAt`
      Se invoca `getUpdatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setAccountFrom`
      Se invoca `setAccountFrom` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getAccountFrom`
      Se invoca `getAccountFrom` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setAccountTo`
      Se invoca `setAccountTo` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getAccountTo`
      Se invoca `getAccountTo` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setIdempotencyKey`
      Se invoca `setIdempotencyKey` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getIdempotencyKey`
      Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getTransactionId`
      Se invoca `getTransactionId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getOperationNumber`
      Se invoca `getOperationNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getChannel`
      Se invoca `getChannel` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getAmount`
      Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getStatus`
      Se invoca `getStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getCreatedAt`
      Se invoca `getCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getUpdatedAt`
      Se invoca `getUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getAccountFrom`
      Se invoca `getAccountFrom` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getAccountTo`
      Se invoca `getAccountTo` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getIdempotencyKey`
      Se invoca `getIdempotencyKey` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getAccountFrom`
      Se invoca `getAccountFrom` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getAccountTo`
      Se invoca `getAccountTo` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getAmount`
      Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getOperationNumber`
      Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getTransactionId`
      Se invoca `getTransactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getChannel`
      Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getCreatedAt`
      Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getIdempotencyKey`
      Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.operationNumber`
      Se invoca `operationNumber` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.channel`
      Se invoca `channel` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.amount`
      Se invoca `amount` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.accountFrom`
      Se invoca `accountFrom` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.accountTo`
      Se invoca `accountTo` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getStatus`
      Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getTransactionId`
      Se invoca `getTransactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getCreatedAt`
      Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getOperationNumber`
      Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getChannel`
      Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getAmount`
      Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getUpdatedAt`
      Se invoca `getUpdatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getAccountFrom`
      Se invoca `getAccountFrom` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getAccountTo`
      Se invoca `getAccountTo` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `ErrorResponse.getField`
      Se invoca `getField` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `ErrorResponse.getDefaultMessage`
      Se invoca `getDefaultMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `Transaction.setIdempotencyKey`
      Se invoca `setIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `Transaction.transactionId`
      Se invoca `transactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`
      io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (15)

- `pom.xml`
- `src/main/java/com/bancadigital/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bancadigital/domain/model/Transaction.java`
- `src/main/java/com/bancadigital/domain/port/TransactionRepository.java`
- `src/main/java/com/bancadigital/domain/port/AccountSystemClient.java`
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java`
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java`
- `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java`
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java`
- `src/main/java/com/bancadigital/config/IdempotencyConfig.java`
- `src/main/java/com/bancadigital/config/ResilienceConfig.java`
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java`
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java`
- `src/test/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClientTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bancadigital/domain`
- `src/main/java/com/bancadigital/application`
- `src/main/java/com/bancadigital/infrastructure`
- `src/main/java/com/bancadigital/config`
- `src/main/resources`
- `src/test/java/com/bancadigital`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas reactivas para alta demanda**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).
- Mision: Jors es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
