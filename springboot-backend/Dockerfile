FROM openjdk:17
EXPOSE 8080
WORKDIR /app
COPY target/springboot-backend-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]