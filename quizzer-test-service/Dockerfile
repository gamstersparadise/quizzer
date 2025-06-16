# Build stage
FROM gradle:8.4-jdk17-jammy AS build
WORKDIR /app

# Copy build files first to leverage Docker cache
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties .
COPY src src

# Build the application
RUN gradle --no-daemon clean build

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the built application
COPY --from=build /app/build/libs/*-all.jar app.jar

# Expose the port your Ktor application listens on
EXPOSE 8007

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]