# !/bin/bash

SERVER_ENV="prod"

mvn clean install -DskipTests
cd app/shumishumi-bootstrap 
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=${SERVER_ENV}"
