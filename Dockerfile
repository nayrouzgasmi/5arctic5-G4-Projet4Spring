FROM maven:3.9.4-eclipse-temurin-11 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
EXPOSE 8082
CMD ["java", "-jar", "DevOps_Project-1.0.jar"]