FROM openjdk:11-jre-slim

WORKDIR /app

COPY /target/5ArcTic5-G4-Projet4Spring.jar 5ArcTic5-G4-Projet4Spring.jar

EXPOSE 8082

CMD ["java", "-jar", "5ArcTic5-G4-Projet4Spring.jar"]
