FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Crypto-Price-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT["java","-jar","Crypto-Price-Service-0.0.1-SNAPSHOT.jar"]