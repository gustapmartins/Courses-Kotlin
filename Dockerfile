FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ADD /target/restFull-0.0.1-SNAPSHOT.jar restFull.jar
ENTRYPOINT ["java", "-jar", "restFull.jar"]
