FROM openjdk:11-jre-slim

WORKDIR /app

CMD pwd

COPY /target/5ArcTic5-G4-Projet4Spring.jar 5ArcTic5-G4-Projet4Spring.jar

CMD pwd
CMD ls -al

EXPOSE 8082

CMD ["java", "-jar", "/app/5ArcTic5-G4-Projet4Spring.jar"]
