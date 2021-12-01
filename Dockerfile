FROM openjdk:17-alpine

WORKDIR /apps/testSringApp

COPY ./target/TestSpringApp-0.0.1-SNAPSHOT.war app.jar

ENTRYPOINT ["java","-jar","app.jar"]