# Stage 1: Build
FROM maven:3.9.5-openjdk-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/syscluBackend-0.0.1-SNAPSHOT.jar syscluBackend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
