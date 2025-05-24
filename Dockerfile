# Use an official OpenJDK image with Debian as the base for the build stage
FROM openjdk:17-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Download dependencies
RUN mvn dependency:go-offline

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK image as the base for the runtime stage
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/rest/target/book-store-backend.jar /app/book-store-backend.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/book-store-backend.jar"]