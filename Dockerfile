#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN chmod +x mvnw
#RUN ./mvnw dependency:resolve
#COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]


FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

ENV NEXUS_URL=http://172.17.0.1:8081/#browse/browse:maven-releases
ENV ARTIFACT_ID=DevOps_Project
ENV VERSION=1.0

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
