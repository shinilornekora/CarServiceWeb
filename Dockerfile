FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests
COPY target/CarServiceWeb-*.jar /app/CarServiceWeb.jar
EXPOSE 8085
CMD ["java", "-jar", "/app/CarServiceWeb.jar"]
