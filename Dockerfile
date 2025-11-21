# Etapa 1: Build (compila o projeto)
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests -B

# Etapa 2: Runtime (executa a aplicação)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*-dev.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
