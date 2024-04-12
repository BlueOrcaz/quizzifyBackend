FROM eclipse-temurin:21-jdk-alpine AS build
COPY . .
RUN mvn clean package -DskipTests
COPY --from=build /target/Quizzify-0.0.1-SNAPSHOT.jar Quizzify.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Quizzify.jar"]