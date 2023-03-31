FROM openjdk:17
WORKDIR /
COPY build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]