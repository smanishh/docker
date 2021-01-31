FROM openjdk:8
ADD target/spring-web-hello-1.jar spring-web-hello-1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-web-hello-1.jar"]