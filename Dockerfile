# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application’s source code
COPY src ./src

# Package the application (build the JAR file)
RUN mvn clean package -DskipTests

# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/jobportal-0.0.1-SNAPSHOT.jar /app/jobportal-0.0.1-SNAPSHOT.jar

# Expose port 8080
EXPOSE 9090

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/jobportal-0.0.1-SNAPSHOT.jar"]

