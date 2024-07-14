# Build stage
FROM --platform=$BUILDPLATFORM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM --platform=$TARGETPLATFORM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]

# Build commands:
# docker buildx create --name mybuilder --use
# docker buildx build --platform linux/amd64,linux/arm64 -t doanvinhphu321/hsf301_badmintonrentalservice:latest --push .
