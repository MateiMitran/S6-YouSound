FROM openjdk:17
WORKDIR /
COPY build/libs/user-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/user-service-1.0-SNAPSHOT.jar"]