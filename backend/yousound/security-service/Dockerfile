FROM openjdk:17
WORKDIR /
COPY build/libs/security-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]