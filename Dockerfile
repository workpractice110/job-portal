# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the project’s jar file to the container
COPY target/jobportal-0.0.1-SNAPSHOT.jar /app/jobportal-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 9090

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/jobportal-0.0.1-SNAPSHOT.jar"]
