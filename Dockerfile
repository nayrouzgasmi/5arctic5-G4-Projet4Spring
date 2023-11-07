FROM openjdk:11

WORKDIR /app


COPY /target/5ArcTic5-G4-Projet4Spring.jar 5ArcTic5-G4-Projet4Spring.jar


EXPOSE 8082

CMD ["java", "-jar", "/app/5ArcTic5-G4-Projet4Spring.jar"]
