# 1. Base Image: Use a lightweight JDK image
FROM openjdk:17-jdk-slim

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the built JAR file from the target directory to the container
COPY target/*.jar jobms.jar

# 4. Expose the application port
EXPOSE 8082

# 5. Command to run the application
CMD ["java", "-jar", "jobms.jar"]
