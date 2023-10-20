FROM arm64v8/openjdk:8-jdk-alpine
COPY target/app-api-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
