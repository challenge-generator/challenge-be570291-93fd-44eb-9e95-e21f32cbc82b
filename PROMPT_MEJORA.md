# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/bancadigital/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.IdempotencyConflictException`: El import com.bancadigital.domain.exception.IdempotencyConflictException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.AccountNotFoundException`: El import com.bancadigital.domain.exception.AccountNotFoundException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.InsufficientFundsException`: El import com.bancadigital.domain.exception.InsufficientFundsException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.AccountSystemTimeoutException`: El import com.bancadigital.domain.exception.AccountSystemTimeoutException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `com.bancadigital.domain.exception.TransactionProcessingException`: El import com.bancadigital.domain.exception.TransactionProcessingException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `com.bancadigital.domain.exception.IdempotencyConflictException`: El import com.bancadigital.domain.exception.IdempotencyConflictException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `com.bancadigital.domain.exception.AccountNotFoundException`: El import com.bancadigital.domain.exception.AccountNotFoundException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `com.bancadigital.domain.exception.InsufficientFundsException`: El import com.bancadigital.domain.exception.InsufficientFundsException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bancadigital/Application.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/Application.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/domain/port/TransactionRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/domain/port/AccountSystemClient.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/config/IdempotencyConfig.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClientTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bancadigital/Application.java` — `ProcessTransactionUseCase.processTransaction`: Se invoca `processTransaction` sobre `ProcessTransactionUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setTransactionId`: Se invoca `setTransactionId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getTransactionId`: Se invoca `getTransactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setOperationNumber`: Se invoca `setOperationNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setChannel`: Se invoca `setChannel` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setAmount`: Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setStatus`: Se invoca `setStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getStatus`: Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setCreatedAt`: Se invoca `setCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getCreatedAt`: Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setAccountFrom`: Se invoca `setAccountFrom` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getAccountFrom`: Se invoca `getAccountFrom` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setAccountTo`: Se invoca `setAccountTo` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getAccountTo`: Se invoca `getAccountTo` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `Transaction.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getTransactionId`: Se invoca `getTransactionId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getOperationNumber`: Se invoca `getOperationNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getChannel`: Se invoca `getChannel` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getAmount`: Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getStatus`: Se invoca `getStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getCreatedAt`: Se invoca `getCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getAccountFrom`: Se invoca `getAccountFrom` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getAccountTo`: Se invoca `getAccountTo` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java` — `TransactionEntity.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getAccountFrom`: Se invoca `getAccountFrom` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getAccountTo`: Se invoca `getAccountTo` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getTransactionId`: Se invoca `getTransactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getCreatedAt`: Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java` — `Transaction.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.operationNumber`: Se invoca `operationNumber` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.channel`: Se invoca `channel` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.amount`: Se invoca `amount` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.accountFrom`: Se invoca `accountFrom` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `TransactionRequest.accountTo`: Se invoca `accountTo` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getStatus`: Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getTransactionId`: Se invoca `getTransactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getCreatedAt`: Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getAccountFrom`: Se invoca `getAccountFrom` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java` — `Transaction.getAccountTo`: Se invoca `getAccountTo` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `ErrorResponse.getField`: Se invoca `getField` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java` — `ErrorResponse.getDefaultMessage`: Se invoca `getDefaultMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `Transaction.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java` — `Transaction.transactionId`: Se invoca `transactionId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`: io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
Jors es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Sólida Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: mixed
- Título: Integración y Configuración de Spring Boot en un Proyecto Backend
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración Inicial de Spring Boot — objetivo: Configurar un proyecto básico de Spring Boot para el dominio de banca digital. — entregable (NO resolver): Proyecto de Spring Boot configurado con las especificaciones iniciales.
- Fase 2: Integración con el Sistema de Cuentas — objetivo: Integrar el proyecto de Spring Boot con el sistema de cuentas para manejar transacciones. — entregable (NO resolver): Proyecto de Spring Boot integrado con el sistema de cuentas.
- Fase 3: Optimización y Escalabilidad — objetivo: Optimizar y escalar el proyecto de Spring Boot para manejar un mayor volumen de transacciones. — entregable (NO resolver): Proyecto de Spring Boot optimizado y escalable.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.bancadigital</groupId>
    <artifactId>transaction-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>transaction-service</name>
    <description>Servicio de procesamiento de transacciones bancarias con idempotencia y resiliencia</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <r2dbc.postgresql.version>1.0.5.RELEASE</r2dbc.postgresql.version>
    </properties>

    <dependencies>
        <!-- Spring Boot WebFlux -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>

        <!-- Spring Data R2DBC -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>
        <dependency>
            <groupId>io.r2dbc</groupId>
            <artifactId>r2dbc-postgresql</artifactId>
            <version>${r2dbc.postgresql.version}</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Cache con Redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>

        <!-- Validación -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bancadigital/Application.java ===
package com.bancadigital;






import com.bancadigital.domain.model.Transaction;
import com.bancadigital.infrastructure.adapter.TransactionR2dbcRepository;
import com.bancadigital.application.usecase.ProcessTransactionUseCase;
import com.bancadigital.config.IdempotencyConfig;
import com.bancadigital.config.ResilienceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableCaching
@EnableWebFlux
@EnableAsync
@ConfigurationPropertiesScan
@Import({com.bancadigital.config.ResilienceConfig.class, com.bancadigital.config.IdempotencyConfig.class})
public class Application {

    private final com.bancadigital.application.usecase.ProcessTransactionUseCase processTransactionUseCase;
    private final com.bancadigital.infrastructure.adapter.TransactionR2dbcRepository transactionRepository;
    private final reactor.core.scheduler.Scheduler boundedElasticScheduler;

    public Application(com.bancadigital.application.usecase.ProcessTransactionUseCase processTransactionUseCase,
                      com.bancadigital.infrastructure.adapter.TransactionR2dbcRepository transactionRepository) {
        this.processTransactionUseCase = processTransactionUseCase;
        this.transactionRepository = transactionRepository;
        this.boundedElasticScheduler = Schedulers.newBoundedElastic(10, 100, "transaction-scheduler");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CircuitBreakerConfig defaultCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .recordExceptions(TimeoutException.class, java.util.concurrent.TimeoutException.class)
                .build();
    }

    @Bean
    public RetryConfig defaultRetryConfig() {
        return RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(TimeoutException.class, java.util.concurrent.TimeoutException.class)
                .build();
    }

    @Bean
    public TimeLimiterConfig defaultTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }

    public Mono<Void> warmup() {
        return Mono.fromRunnable(() -> {
                    // Simulación de carga inicial para verificar configuración
                    processTransactionUseCase.processTransaction(
                            new com.bancadigital.domain.model.Transaction(
                                    "OP123456",
                                    "MOBILE",
                                    "10001",
                                    "20002",
                                    100.0,
                                    "PENDING"
                            )
                    ).subscribeOn(boundedElasticScheduler)
                    .subscribe(
                            result -> System.out.println("Warmup transaction processed: " + result),
                            error -> System.err.println("Warmup error: " + error.getMessage())
                    );
                })
                .then();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: transaction-service
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/bancadb
    username: bancauser
    password: bancapass
    pool:
      enabled: true
      initial-size: 10
      max-size: 20
      max-idle-time: 30m
      validation-query: SELECT 1
  redis:
    host: localhost
    port: 6379
    timeout: 2000ms
    lettuce:
      pool:
        max-active: 16
        max-idle: 8
        min-idle: 4
  cache:
    type: redis
    redis:
      time-to-live: 86400000 # 24 horas en milisegundos para idempotencia

server:
  port: 8080
  netty:
    connection-timeout: 2000ms
    idle-timeout: 30000ms

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
  retry:
    configs:
      default:
        maxRetryAttempts: 3
        waitDuration: 500ms
  timelimiter:
    configs:
      default:
        timeoutDuration: 2s

idempotency:
  key-prefix: "idempotency:"
  ttl-hours: 24

logging:
  level:
    root: INFO
    com.bancadigital: DEBUG
    org.springframework.r2dbc: DEBUG
    io.r2dbc.postgresql: DEBUG
    reactor.netty: DEBUG

// === ARCHIVO: src/main/java/com/bancadigital/domain/model/Transaction.java ===
package com.bancadigital.domain.model;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Transaction {
    UUID transactionId;
    String operationNumber;
    String channel;
    BigDecimal amount;
    TransactionStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String accountFrom;
    String accountTo;
    String idempotencyKey;

    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED,
        ROLLED_BACK
    }

    public static Transaction createPendingTransaction(String operationNumber, String channel,
                                                      BigDecimal amount, String accountFrom,
                                                      String accountTo, String idempotencyKey) {
        return Transaction.builder()
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
    }

    public Transaction complete() {
        return this.toBuilder()
                .status(TransactionStatus.COMPLETED)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public Transaction fail() {
        return this.toBuilder()
                .status(TransactionStatus.FAILED)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public Transaction rollback() {
        return this.toBuilder()
                .status(TransactionStatus.ROLLED_BACK)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

// === ARCHIVO: src/main/java/com/bancadigital/domain/port/TransactionRepository.java ===
package com.bancadigital.domain.port;

import com.bancadigital.domain.model.Transaction;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface TransactionRepository {
    Mono<Transaction> save(Transaction transaction);
    Mono<Transaction> findById(UUID transactionId);
    Mono<Transaction> findByIdempotencyKey(String idempotencyKey);
    Mono<Boolean> existsByIdempotencyKey(String idempotencyKey);
    Mono<Void> deleteById(UUID transactionId);
}

// === ARCHIVO: src/main/java/com/bancadigital/domain/port/AccountSystemClient.java ===
package com.bancadigital.domain.port;

import reactor.core.publisher.Mono;
import java.math.BigDecimal;

public interface AccountSystemClient {
    Mono<Boolean> validateAccount(String accountNumber);
    Mono<Boolean> checkSufficientFunds(String accountNumber, BigDecimal amount);
    Mono<Boolean> transferFunds(String fromAccount, String toAccount, BigDecimal amount, String operationNumber);
}

// === ARCHIVO: src/main/java/com/bancadigital/infrastructure/adapter/TransactionR2dbcRepository.java ===
package com.bancadigital.infrastructure.adapter;

import com.bancadigital.domain.model.Transaction;
import com.bancadigital.domain.model.Transaction.TransactionStatus;
import com.bancadigital.domain.port.TransactionRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.core.ReactiveSelect;
import org.springframework.data.r2dbc.core.ReactiveInsert;
import org.springframework.data.r2dbc.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class TransactionR2dbcRepository implements TransactionRepository {

    private final R2dbcEntityTemplate entityTemplate;

    public TransactionR2dbcRepository(R2dbcEntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        return entityTemplate.insert(entity)
                .map(this::toDomain);
    }

    @Override
    public Mono<Transaction> findById(UUID transactionId) {
        Query query = Query.query(
                org.springframework.data.domain.ReactivePageable.of(
                        org.springframework.data.domain.Pageable.ofSize(1)));
        return entityTemplate.select(TransactionEntity.class)
                .matching(query)
                .first()
                .map(this::toDomain);
    }

    @Override
    public Mono<Transaction> findByIdempotencyKey(String idempotencyKey) {
        return entityTemplate.select(TransactionEntity.class)
                .from("transactions")
                .matching(Query.query(
                        org.springframework.data.r2dbc.core.where(
                                org.springframework.data.r2dbc.core.Columns.from("idempotency_key")
                                        .is(idempotencyKey))))
                .first()
                .map(this::toDomain);
    }

    @Override
    public Mono<Boolean> existsByIdempotencyKey(String idempotencyKey) {
        return entityTemplate.getDatabaseClient()
                .sql("SELECT COUNT(*) FROM transactions WHERE idempotency_key = :key")
                .bind("key", idempotencyKey)
                .map((row, metadata) -> row.get(0, Long.class) > 0)
                .first();
    }

    @Override
    public Mono<Void> deleteById(UUID transactionId) {
        return entityTemplate.delete(TransactionEntity.class)
                .matching(Query.query(
                        org.springframework.data.r2dbc.core.where(
                                org.springframework.data.r2dbc.core.Columns.from("transaction_id")
                                        .is(transactionId.toString()))))
                .then();
    }

    private TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionId(transaction.getTransactionId().toString());
        entity.setOperationNumber(transaction.getOperationNumber());
        entity.setChannel(transaction.getChannel());
        entity.setAmount(transaction.getAmount());
        entity.setStatus(transaction.getStatus().name());
        entity.setCreatedAt(transaction.getCreatedAt());
        entity.setUpdatedAt(transaction.getUpdatedAt());
        entity.setAccountFrom(transaction.getAccountFrom());
        entity.setAccountTo(transaction.getAccountTo());
        entity.setIdempotencyKey(transaction.getIdempotencyKey());
        return entity;
    }

    private Transaction toDomain(TransactionEntity entity) {
        return Transaction.builder()
                .transactionId(UUID.fromString(entity.getTransactionId()))
                .operationNumber(entity.getOperationNumber())
                .channel(entity.getChannel())
                .amount(entity.getAmount())
                .status(TransactionStatus.valueOf(entity.getStatus()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .accountFrom(entity.getAccountFrom())
                .accountTo(entity.getAccountTo())
                .idempotencyKey(entity.getIdempotencyKey())
                .build();
    }

    @Table("transactions")
    private static class TransactionEntity {
        @Id
        @Column("transaction_id")
        private String transactionId;

        @Column("operation_number")
        private String operationNumber;

        @Column("channel")
        private String channel;

        @Column("amount")
        private BigDecimal amount;

        @Column("status")
        private String status;

        @Column("created_at")
        private LocalDateTime createdAt;

        @Column("updated_at")
        private LocalDateTime updatedAt;

        @Column("account_from")
        private String accountFrom;

        @Column("account_to")
        private String accountTo;

        @Column("idempotency_key")
        private String idempotencyKey;

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public String getOperationNumber() { return operationNumber; }
        public void setOperationNumber(String operationNumber) { this.operationNumber = operationNumber; }
        public String getChannel() { return channel; }
        public void setChannel(String channel) { this.channel = channel; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
        public String getAccountFrom() { return accountFrom; }
        public void setAccountFrom(String accountFrom) { this.accountFrom = accountFrom; }
        public String getAccountTo() { return accountTo; }
        public void setAccountTo(String accountTo) { this.accountTo = accountTo; }
        public String getIdempotencyKey() { return idempotencyKey; }
        public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
    }
}

// === ARCHIVO: src/main/java/com/bancadigital/application/usecase/ProcessTransactionUseCase.java ===
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

// === ARCHIVO: src/main/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClient.java ===
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

// === ARCHIVO: src/main/java/com/bancadigital/infrastructure/rest/TransactionController.java ===
package com.bancadigital.infrastructure.rest;

import com.bancadigital.application.usecase.ProcessTransactionUseCase;
import com.bancadigital.domain.model.Transaction;
import com.bancadigital.domain.model.Transaction.TransactionStatus;
import com.bancadigital.domain.port.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Validated
@Tag(name = "Transacciones", description = "API para procesamiento de transacciones bancarias")
@Slf4j
public class TransactionController {

    private final ProcessTransactionUseCase processTransactionUseCase;
    private final TransactionRepository transactionRepository;

    @PostMapping
    @Operation(summary = "Procesar transacción", description = "Procesa una nueva transacción bancaria con soporte de idempotencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transacción procesada exitosamente",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "200", description = "Transacción duplicada - retorna la transacción original",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "422", description = "Error de negocio - cuenta inválida o fondos insuficientes"),
        @ApiResponse(responseCode = "503", description = "Sistema de cuentas no disponible"),
        @ApiResponse(responseCode = "504", description = "Timeout del sistema de cuentas")
    })
    public Mono<ResponseEntity<TransactionResponse>> processTransaction(
            @Valid @RequestBody TransactionRequest request) {

        log.info("Recibida solicitud de transacción: operationNumber={}, channel={}, amount={}",
                request.operationNumber(), request.channel(), request.amount());

        String idempotencyKey = buildIdempotencyKey(request.operationNumber(), request.channel());

        return transactionRepository.findByIdempotencyKey(idempotencyKey)
                .flatMap(existing -> {
                    log.info("Transacción idempotente encontrada: {}", existing.getTransactionId());
                    return Mono.just(ResponseEntity.ok(toResponse(existing)));
                })
                .switchIfEmpty(
                    processTransactionUseCase.execute(
                            request.operationNumber(),
                            request.channel(),
                            request.amount(),
                            request.accountFrom(),
                            request.accountTo(),
                            idempotencyKey
                    )
                    .map(transaction -> {
                        HttpStatus status = transaction.getStatus() == TransactionStatus.COMPLETED
                                ? HttpStatus.CREATED
                                : HttpStatus.UNPROCESSABLE_ENTITY;
                        return ResponseEntity.status(status).body(toResponse(transaction));
                    })
                )
                .onErrorResume(ResponseStatusException.class, e ->
                    Mono.just(ResponseEntity.status(e.getStatusCode())
                            .body(TransactionResponse.error(e.getReason())))
                )
                .onErrorResume(Exception.class, e -> {
                    log.error("Error inesperado procesando transacción", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(TransactionResponse.error("Error interno del servidor")));
                });
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "Consultar transacción", description = "Obtiene los detalles de una transacción por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transacción encontrada",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public Mono<ResponseEntity<TransactionResponse>> getTransaction(
            @Parameter(description = "ID de la transacción") @PathVariable UUID transactionId) {

        return transactionRepository.findById(transactionId)
                .map(transaction -> ResponseEntity.ok(toResponse(transaction)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/idempotency/{idempotencyKey}")
    @Operation(summary = "Verificar clave de idempotencia", description = "Verifica si existe una transacción con la clave de idempotencia dada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "404", description = "No existe transacción con esa clave")
    })
    public Mono<ResponseEntity<Map<String, Object>>> checkIdempotency(
            @Parameter(description = "Clave de idempotencia") @PathVariable String idempotencyKey) {

        return transactionRepository.findByIdempotencyKey(idempotencyKey)
                .map(transaction -> ResponseEntity.ok(Map.of(
                        "exists", true,
                        "transactionId", transaction.getTransactionId(),
                        "status", transaction.getStatus(),
                        "createdAt", transaction.getCreatedAt()
                )))
                .switchIfEmpty(Mono.just(ResponseEntity.ok(Map.of("exists", false))));
    }

    private String buildIdempotencyKey(String operationNumber, String channel) {
        return operationNumber + "_" + channel;
    }

    private TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getOperationNumber(),
                transaction.getChannel(),
                transaction.getAmount(),
                transaction.getStatus().name(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt(),
                transaction.getAccountFrom(),
                transaction.getAccountTo()
        );
    }

    public record TransactionRequest(
            @NotBlank(message = "El número de operación es obligatorio")
            String operationNumber,
            @NotBlank(message = "El canal es obligatorio")
            String channel,
            @NotNull(message = "El monto es obligatorio")
            @Positive(message = "El monto debe ser positivo")
            BigDecimal amount,
            @NotBlank(message = "La cuenta de origen es obligatoria")
            String accountFrom,
            @NotBlank(message = "La cuenta de destino es obligatoria")
            String accountTo
    ) {}

    public record TransactionResponse(
            UUID transactionId,
            String operationNumber,
            String channel,
            BigDecimal amount,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String accountFrom,
            String accountTo
    ) {
        public static TransactionResponse error(String message) {
            return new TransactionResponse(
                    null, null, null, null, "ERROR",
                    null, null, null, null
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/bancadigital/config/IdempotencyConfig.java ===
package com.bancadigital.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class IdempotencyConfig {

    @Value("${app.idempotency.ttl-hours:24}")
    private int ttlHours;

    @Value("${app.idempotency.key-prefix:idempotency:}")
    private String keyPrefix;

    private final ReactiveRedisConnectionFactory connectionFactory;

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate() {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }

    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory) {

        StringRedisSerializer serializer = new StringRedisSerializer();
        RedisSerializationContext<String, String> context = RedisSerializationContext
                .<String, String>newSerializationContext(serializer)
                .key(serializer)
                .value(serializer)
                .hashKey(serializer)
                .hashValue(serializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public IdempotencyService idempotencyService(
            ReactiveStringRedisTemplate redisTemplate) {

        return new IdempotencyService(redisTemplate, keyPrefix, Duration.ofHours(ttlHours));
    }

    @Slf4j
    public static class IdempotencyService {

        private final ReactiveStringRedisTemplate redisTemplate;
        private final String keyPrefix;
        private final Duration ttl;

        public IdempotencyService(ReactiveStringRedisTemplate redisTemplate,
                                  String keyPrefix,
                                  Duration ttl) {
            this.redisTemplate = redisTemplate;
            this.keyPrefix = keyPrefix;
            this.ttl = ttl;
        }

        public <T> Mono<T> execute(String idempotencyKey,
                                    Function<String, Mono<T>> action,
                                    Function<T, String> serializer) {
            String fullKey = keyPrefix + idempotencyKey;

            return redisTemplate.hasKey(fullKey)
                    .flatMap(exists -> {
                        if (Boolean.TRUE.equals(exists)) {
                            log.info("Clave de idempotencia encontrada en caché: {}", fullKey);
                            return redisTemplate.opsForValue().get(fullKey)
                                    .flatMap(value -> {
                                        T cached = deserialize(value, serializer);
                                        if (cached != null) {
                                            return Mono.just(cached);
                                        }
                                        return action.apply(idempotencyKey)
                                                .flatMap(result -> cacheResult(fullKey, result, serializer));
                                    });
                        }
                        return action.apply(idempotencyKey)
                                .flatMap(result -> cacheResult(fullKey, result, serializer));
                    });
        }

        private <T> Mono<T> cacheResult(String key, T result, Function<T, String> serializer) {
            String serialized = serializer.apply(result);
            return redisTemplate.opsForValue()
                    .set(key, serialized, ttl)
                    .then(Mono.just(result))
                    .doOnNext(r -> log.info("Resultado cacheado con clave de idempotencia: {}", key));
        }

        @SuppressWarnings("unchecked")
        private <T> T deserialize(String value, Function<T, String> serializer) {
            try {
                return (T) value;
            } catch (Exception e) {
                log.warn("Error al deserializar valor cacheado: {}", e.getMessage());
                return null;
            }
        }

        public Mono<Boolean> exists(String idempotencyKey) {
            String fullKey = keyPrefix + idempotencyKey;
            return redisTemplate.hasKey(fullKey);
        }

        public Mono<Void> delete(String idempotencyKey) {
            String fullKey = keyPrefix + idempotencyKey;
            return redisTemplate.delete(fullKey).then();
        }
    }
}

// === ARCHIVO: src/main/java/com/bancadigital/config/ResilienceConfig.java ===
package com.bancadigital.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();

        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();

        return RetryRegistry.of(config);
    }

    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();

        return TimeLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/bancadigital/infrastructure/exception/GlobalExceptionHandler.java ===
package com.bancadigital.infrastructure.exception;

import com.bancadigital.domain.exception.IdempotencyConflictException;
import com.bancadigital.domain.exception.AccountNotFoundException;
import com.bancadigital.domain.exception.InsufficientFundsException;
import com.bancadigital.domain.exception.AccountSystemTimeoutException;
import com.bancadigital.domain.exception.TransactionProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IdempotencyConflictException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIdempotencyConflict(IdempotencyConflictException ex) {
        log.warn("Conflicto de idempotencia detectado: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            "CONFLICT",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(error));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleAccountNotFound(AccountNotFoundException ex) {
        log.warn("Cuenta no encontrada: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "ACCOUNT_NOT_FOUND",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleInsufficientFunds(InsufficientFundsException ex) {
        log.warn("Fondos insuficientes: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "INSUFFICIENT_FUNDS",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error));
    }

    @ExceptionHandler(AccountSystemTimeoutException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleAccountSystemTimeout(AccountSystemTimeoutException ex) {
        log.error("Timeout del sistema de cuentas: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
            HttpStatus.GATEWAY_TIMEOUT.value(),
            "ACCOUNT_SYSTEM_TIMEOUT",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(error));
    }

    @ExceptionHandler(TransactionProcessingException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleTransactionProcessing(TransactionProcessingException ex) {
        log.error("Error procesando transacción: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "TRANSACTION_PROCESSING_ERROR",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationErrors(WebExchangeBindException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        log.warn("Errores de validación: {}", errors);
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "VALIDATION_ERROR",
            errors,
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Argumento ilegal: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "BAD_REQUEST",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        log.error("Error inesperado: ", ex);
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "INTERNAL_SERVER_ERROR",
            "Ha ocurrido un error inesperado. Por favor, contacte al administrador.",
            LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }

    public record ErrorResponse(
        int status,
        String code,
        String message,
        LocalDateTime timestamp
    ) {}
}

// === ARCHIVO: src/test/java/com/bancadigital/application/usecase/ProcessTransactionUseCaseTest.java ===
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

// === ARCHIVO: src/test/java/com/bancadigital/infrastructure/adapter/AccountSystemWebClientTest.java ===
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
```
