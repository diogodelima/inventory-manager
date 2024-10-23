FROM openjdk:21-jdk

WORKDIR /app

COPY /build/libs/users-service-1.0.jar /app/users-service-1.0.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/users-service-1.0.jar"]