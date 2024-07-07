#FROM eclipse-temurin:17.0.11_9-jre-ubi9-minimal
FROM maven:3.8.5-openjdk-17-slim
RUN apt-get update && apt-get install -y \
    vim \
    bash-completion \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/processor-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/config/
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "app.jar","--spring.config.location=/app/config/application.properties"]