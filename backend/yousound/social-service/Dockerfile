FROM openjdk:17
WORKDIR /
COPY build/libs/social-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/social-service-1.0-SNAPSHOT.jar"]