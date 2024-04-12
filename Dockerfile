FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar Quizzify-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Quizzify-0.0.1-SNAPSHOT.jar"]
