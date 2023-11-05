FROM openjdk:8-jre-slim
WORKDIR /app
COPY /target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
EXPOSE 8082
CMD ["java", "-jar", "DevOps_Project-1.0.jar"]
