# Stage 1: Build con Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Copiamos los archivos del proyecto
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Dar permisos de ejecución a mvnw
RUN chmod +x mvnw

# Compilar sin tests
RUN ./mvnw clean package -DskipTests

# Stage 2: Imagen final mínima
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build target/asistente-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]