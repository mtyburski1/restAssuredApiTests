# Base image with OpenJDK 17 and Maven installed
FROM maven:3.9.4-eclipse-temurin-17

# Install git
USER root
RUN apt-get update && apt-get install -y git && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy everything into container
COPY . .

# Run Maven to build (skip tests here, tests will run in Jenkins pipeline explicitly)
RUN mvn clean install -DskipTests

# Default command (optional)
CMD ["mvn", "test"]
