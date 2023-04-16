FROM openjdk:17
WORKDIR /
COPY build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/music-service-0.0.1-SNAPSHOT.jar"]