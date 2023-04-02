FROM openjdk:17
WORKDIR /
COPY build/libs/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]