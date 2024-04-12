# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS build

# Install Maven
RUN apk add --no-cache maven

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Copy artifacts and create the final image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Quizzify-0.0.1-SNAPSHOT.jar Quizzify.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Quizzify.jar"]
