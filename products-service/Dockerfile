FROM openjdk:21-jdk

WORKDIR /app

COPY /build/libs/products-service-1.0.jar /app/products-service-1.0.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/products-service-1.0.jar"]