FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY /target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar

# Expose the port your Spring Boot application will run on
EXPOSE 8082

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "DevOps_Project-1.0.jar"]