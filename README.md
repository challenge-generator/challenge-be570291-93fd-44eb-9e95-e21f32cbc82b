# Integración y Configuración de Spring Boot en un Proyecto Backend

Jors, un desarrollador Backend Advanced, necesita profundizar en la integración y configuración de Spring Boot en un proyecto de banca digital. El proyecto involucra la gestión de cuentas bancarias, donde los actores principales son el 'cliente', el'sistema de cuentas' y el'motor de transacciones'. El sistema debe manejar un volumen de 10 000 transacciones por segundo con un SLA del 99.9%. Jors debe asegurar la idempotencia de las transacciones por número de operación y canal, donde dos invocaciones con la misma clave producen un solo registro y devuelven la misma respuesta dentro de una ventana de 24 horas. El modo de falla específico a considerar es el timeout del sistema de cuentas mayor a 2 segundos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Sólida Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración Inicial de Spring Boot

**Objetivo:** Configurar un proyecto básico de Spring Boot para el dominio de banca digital.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el proyecto de Spring Boot para que pueda manejar un volumen de 10 000 transacciones por segundo.
- Asegurar la idempotencia de las transacciones por número de operación y canal.
- Gestionar el timeout del sistema de cuentas mayor a 2 segundos.

**Entregable:** Proyecto de Spring Boot configurado con las especificaciones iniciales.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la configuración inicial de Spring Boot en proyectos de alta demanda.
- Piensa en cómo manejarías el timeout del sistema de cuentas en un entorno de alta disponibilidad.

</details>

### Fase 2: Integración con el Sistema de Cuentas

**Objetivo:** Integrar el proyecto de Spring Boot con el sistema de cuentas para manejar transacciones.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Integrar el proyecto de Spring Boot con el sistema de cuentas para que pueda manejar transacciones.
- Asegurar que las transacciones sean idempotentes y manejar el timeout del sistema de cuentas.

**Entregable:** Proyecto de Spring Boot integrado con el sistema de cuentas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo integrar Spring Boot con otros sistemas y servicios.
- Piensa en cómo asegurar la idempotencia y manejar errores específicos del dominio.

</details>

### Fase 3: Optimización y Escalabilidad

**Objetivo:** Optimizar y escalar el proyecto de Spring Boot para manejar un mayor volumen de transacciones.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Optimizar y escalar el proyecto de Spring Boot para manejar un mayor volumen de transacciones.
- Asegurar que el sistema mantenga la idempotencia y maneje el timeout del sistema de cuentas.

**Entregable:** Proyecto de Spring Boot optimizado y escalable.

<details>
<summary>Pistas de conocimiento</summary>

- Considera técnicas de optimización y escalabilidad para sistemas de alta demanda.
- Piensa en cómo asegurar la idempotencia y manejar errores en un entorno escalable.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es Spring Boot y para qué se utiliza en el contexto de un proyecto de banca digital?
- **paraQueSirve**: ¿Para qué sirve la integración de Spring Boot con el sistema de cuentas en el contexto de transacciones bancarias?
- **comoSeUsa**: ¿Cómo se usa Spring Boot para asegurar la idempotencia de las transacciones en el contexto de un proyecto de banca digital?
- **erroresComunes**: ¿Cuáles son los errores comunes al integrar Spring Boot con otros sistemas y servicios en el contexto de un proyecto de banca digital?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización y escalabilidad de un proyecto de Spring Boot en el contexto de un sistema de banca digital?

## Criterios de Evaluacion

- Configuración inicial de Spring Boot para un proyecto de banca digital.
- Integración con el sistema de cuentas para manejar transacciones.
- Optimización y escalabilidad del proyecto para manejar un mayor volumen de transacciones.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
