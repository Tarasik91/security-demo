# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim


# Add the application's jar to the image
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

# Command to execute the application
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]