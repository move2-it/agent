FROM maven:3.9.4-eclipse-temurin-11-alpine AS build

COPY pom.xml .

COPY src ./src

RUN mvn package

FROM eclipse-temurin:11-jre-alpine AS run

COPY --from=build target/app.jar .

ENTRYPOINT ["java", "-jar", "app.jar"]