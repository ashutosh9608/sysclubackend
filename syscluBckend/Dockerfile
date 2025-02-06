# Stage 1: Build
FROM maven:3.9.5-openjdk-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/comp-db-ms0-0-1.0.jar comp-db-ms0-0-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "comp-db-ms0-0-1.0.jar"]
