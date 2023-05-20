FROM openjdk:17
WORKDIR /
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-gateway-0.0.1-SNAPSHOT.jar"]