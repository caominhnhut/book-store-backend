# Use an official OpenJDK image with Debian as the base for the build stage
FROM maven:3.5.3-jdk-8-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the application
RUN mvn install -DskipTests

# Use an official OpenJDK image as the base for the runtime stage
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /run

# Copy the built JAR file from the build stage
COPY --from=build /app/rest/target/book-store-backend.jar /run/book-store-backend.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/run/book-store-backend.jar"]