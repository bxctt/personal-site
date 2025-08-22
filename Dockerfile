FROM openjdk:17

COPY site/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
