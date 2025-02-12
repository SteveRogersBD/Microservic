# 1. Base Image: Use a lightweight JDK image
FROM eclipse-temurin:17-jdk

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the built JAR file from the target directory to the container
COPY target/*.jar review-service.jar

# 4. Expose the application port
EXPOSE 8083

# 5. Command to run the application
CMD ["java", "-jar", "review-service.jar"]
