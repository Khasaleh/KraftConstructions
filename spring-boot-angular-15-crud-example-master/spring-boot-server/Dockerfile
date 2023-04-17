# base image
FROM openjdk:17-jdk-slim

# set working directory
WORKDIR /app

# copy jar file
COPY target/spring-boot-jpa-h2-0.0.1-SNAPSHOT.jar /app/spring-boot-jpa-h2-0.0.1-SNAPSHOT.jar

# expose port
EXPOSE 8081

# start the app
CMD ["java", "-jar", "/app/spring-boot-jpa-h2-0.0.1-SNAPSHOT.jar"]
