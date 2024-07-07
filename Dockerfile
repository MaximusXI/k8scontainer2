FROM eclipse-temurin:17.0.11_9-jre-ubi9-minimal

WORKDIR /app

COPY target/processor-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/config/
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "app.jar","--spring.config.location=/app/config/application.properties"]