# !/bin/bash

mvn clean install -DskipTests
cd app/shumishumi-bootstrap
./mvnw spring-boot:run