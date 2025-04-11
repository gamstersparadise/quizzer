FROM gradle:8.4-jdk17-jammy AS build
WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties .
COPY gradle gradle
COPY src src

RUN gradle --no-daemon clean build

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/build/libs/*-all.jar app.jar

EXPOSE 8008

ENTRYPOINT ["java", "-jar", "app.jar"]