# Base image
FROM maven:3.9.1-eclipse-temurin-17
COPY . .

# App install
RUN mvn clean install
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/target/SuperbowlBackend-0.1.0-SNAPSHOT.war"]