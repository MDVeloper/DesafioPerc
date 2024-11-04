FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp

COPY snapshot/ms-main-*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]