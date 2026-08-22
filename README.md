# Skyscanner Software Engineering - Task 2

Dropwizard microservice for searching hotels and rental cars by city.

## Requirements
- OpenJDK 19
- Maven 3.6+

## Run
```bash
mvn clean package
java -jar target/skyscanner-microservice-1.0.0.jar server config.yml
```

## Test
POST to:
`http://localhost:8080/search`

Body:
```json
{"city":"petalborough"}
```

Content-Type:
`application/json`

Try:
- petalborough
- rustburg
- shaleport
- an invalid city

The endpoint returns the matching hotel and rental-car results.
