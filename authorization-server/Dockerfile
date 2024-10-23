FROM openjdk:21-jdk

WORKDIR /app

COPY /build/libs/authorization-server-1.0.jar /app/authorization-server-1.0.jar

EXPOSE 9000

CMD ["java", "-jar", "/app/authorization-server-1.0.jar"]