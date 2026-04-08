# Asistente Reclutador IA

Aplicación de **apoyo al reclutamiento**: pegas el texto de un CV y la descripción de un puesto, y el sistema devuelve un análisis orientado a ver qué tan encaja el candidato con la búsqueda.

## Qué hay en este repo

Este proyecto es el **backend** (servidor). La parte visual (**frontend**) puede vivir en otro proyecto; aquí solo se prepara la API para que una web o app la consuma.

## Tecnología (resumen)

- **Java 17** y **Spring Boot**: el servidor REST.
- **API de OpenAI** a través de **Spring AI**: el “cerebro” que lee el CV y el puesto y genera el informe.
- Modelo usado: **GPT-4o mini** (configurado como `gpt-4o-mini`), adecuado para tareas de texto con buen equilibrio costo/calidad.

No hace falta conocer los detalles del framework para entender el flujo: el usuario envía datos → el backend llama a OpenAI → devuelve un resultado estructurado (resumen, habilidades detectadas, seniority estimado, porcentaje de match, fortalezas, vacíos y recomendaciones).

## Requisitos

- **JDK 17** (si compilás en tu máquina).
- Una **API key de OpenAI** válida.

## Configuración

Variables de entorno útiles:

| Variable | Para qué sirve |
|----------|----------------|
| `OPENAI_API_KEY` | **Obligatoria** para que funcione el análisis. |
| `PORT` | Puerto del servidor (por defecto **8080**). |
| `FRONTEND_URL` | Origen de tu frontend en producción (ej. `https://mi-app.com`). En desarrollo ya se contemplan orígenes típicos como `http://localhost:3000` y `http://localhost:5173`. |

## Cómo ejecutarlo (local)

Con Maven (desde la raíz del proyecto):

```bash
./mvnw spring-boot:run
```

En Windows también podés usar `mvnw.cmd`.

## Docker

El repo incluye un `Dockerfile` que compila el proyecto y levanta la app en un contenedor. Construí la imagen y ejecutala según tu entorno (asegurate de pasar `OPENAI_API_KEY` al contenedor).

## Endpoints principales

- **Salud del servicio:** `GET /health` — indica si el backend está arriba.
- **Análisis de CV:** `POST /api/recruiter/analyze` — cuerpo JSON con el texto del CV y la descripción del trabajo (`cvText`, `jobDescription`).

El frontend (cuando lo tengas) debe apuntar a la URL base de este backend (por ejemplo `http://localhost:8080`) y llamar a esos endpoints.

## Nota

Los textos de CV y descripción se acotan en longitud en el servidor para mantener las peticiones razonables; el modelo devuelve el análisis en **español** según la lógica definida en el servicio.
