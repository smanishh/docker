FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8
ADD --from=build /usr/src/app/target/spring-web-hello-1.jar spring-web-hello-1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-web-hello-1.jar"]