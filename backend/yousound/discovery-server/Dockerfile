FROM openjdk:17
WORKDIR /
COPY build/libs/discovery-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/discovery-server-0.0.1-SNAPSHOT.jar"]