FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar 
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/application.jar"]
