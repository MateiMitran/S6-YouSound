FROM openjdk:17
WORKDIR /
COPY build/libs/*.jar music-service-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/music-service-0.0.1-SNAPSHOT.jar"]