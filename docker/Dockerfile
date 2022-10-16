# Build the application
FROM maven:3.8.6-amazoncorretto-17 as builder
WORKDIR /app
COPY ../pom.xml .
COPY ../src ./src
RUN mvn package

# Run it from a minimal linux distro
FROM amazoncorretto:17.0.4-alpine3.15
COPY --from=builder /app/target/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
