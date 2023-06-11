
# stage 1: build the maven project
FROM maven:3.8.3-openjdk-11-slim AS builder
WORKDIR /build
COPY . .
RUN mvn clean install -DskipTests

# stage 2 : create the final docker image
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=builder /build/target/shumishumi-executable.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]
