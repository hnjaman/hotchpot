FROM openjdk:latest
ADD target/result-automation-0.0.1-SNAPSHOT.jar result-automation.jar
ENTRYPOINT ["java", "-jar", "result-automation.jar"]
EXPOSE 8080