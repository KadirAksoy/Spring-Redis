FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Spring-Redis-0.0.1-SNAPSHOT.jar
ENTRYPOINT["java","-jar","Spring-Redis-0.0.1-SNAPSHOT.jar"]
