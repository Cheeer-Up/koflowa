# backend/Dockerfile
#FROM openjdk:11-jdk-alpine
FROM adoptopenjdk/openjdk11

COPY build/libs/koflowa-0.0.1-SNAPSHOT.jar koflowa.jar

# properties 실행 명령어
ENTRYPOINT ["java","-jar","koflowa.jar"]