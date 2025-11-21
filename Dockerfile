# Etapa 1: Build (compila o projeto)
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copia o pom.xml e baixa dependências (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia todo o código fonte
COPY src ./src

# Compila o projeto
RUN mvn clean package -DskipTests -B

# Etapa 2: Runtime (executa a aplicação)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o JAR compilado da etapa anterior
COPY --from=build /app/target/quarkus-app/ ./

# Expõe a porta 8080
EXPOSE 8080

# Executa a aplicação
CMD ["java", "-jar", "quarkus-run.jar"]
