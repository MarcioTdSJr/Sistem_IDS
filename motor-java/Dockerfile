# Estágio 1: Build (Compila o código e gera o .jar usando Maven)
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Runtime Otimizado (Apenas o Java JRE leve para rodar a aplicação)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/motor-ids-1.0-SNAPSHOT.jar ./app.jar
CMD ["java", "-cp", "app.jar", "com.sistemids.AnalisadorDeTrafego"]