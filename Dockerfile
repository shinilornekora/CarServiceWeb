FROM openjdk:17-jdk-slim
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests
COPY target/CarServiceWeb-*.jar /app/CarServiceWeb.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/CarServiceWeb.jar"]
