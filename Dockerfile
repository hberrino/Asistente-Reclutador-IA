# Imagen base con Maven y JDK 17
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Copiamos los archivos del proyecto
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Compilar sin tests
RUN ./mvnw clean package -DskipTests

# Imagen final mínima
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build target/asistente-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]