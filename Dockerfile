## Use an official OpenJDK runtime as a parent image
#FROM openjdk:19
#
## Set the working directory to /app
#WORKDIR /app
#
## Copy the JAR file from the host to the container
#COPY target/project-1.0.jar /app/app.jar
#EXPOSE 8089
## Specify the command to run your application
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY target/project-1.0.jar /app/app.jar

#ENV NEXUS_URL=http://172.17.0.2:8081/#browse/browse:maven-releases
#ENV ARTIFACT_ID=DevOps_Project
#ENV VERSION=1.0

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
