FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn clean package -DskipTests
CMD ["java", "-cp", "target/motor-ids-1.0-SNAPSHOT.jar", "com.sistemids.AnalisadorDeTrafego"]