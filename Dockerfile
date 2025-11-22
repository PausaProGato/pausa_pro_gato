# ===========================
# 1) Build Stage
# ===========================
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia apenas arquivos essenciais primeiro (melhor cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Agora copia o restante do código
COPY src src

# Gera o pacote (JVM mode)
RUN ./mvnw package -DskipTests

# ===========================
# 2) Runtime Stage
# ===========================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia somente a aplicação empacotada
COPY --from=build /app/target/quarkus-app/ ./quarkus-app/

# Porta padrão do Quarkus (caso Render precise)
EXPOSE 8080

CMD ["java", "-jar", "quarkus-app/quarkus-run.jar"]

