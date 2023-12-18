FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar bank.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/bank.jar"]
