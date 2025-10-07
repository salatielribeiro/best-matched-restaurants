# Best Restaurants API

Small Spring Boot REST API that returns the top 5 restaurant matches from CSV files.

## Requirements
- Java 17+
- Maven 3.9+

## Build
```bash
Inside project folder:

mvn -DskipTests clean package
```
The JAR will be generated in `target/` (e.g., `best-restaurants-0.0.1-SNAPSHOT.jar`).

## Run (classpath CSVs)
```bash
java -jar target/best-restaurants-0.0.1-SNAPSHOT.jar
```
Runs at `http://localhost:8080`.

## Tests
```bash
mvn test
```
(Or run specific classes from your IDE.)

## Endpoint
**GET** `/restaurants/search`  
Provide at least one of: `name`, `customerRating`, `distance`, `price`, `cuisineName`.

### Browser examples
Open in your browser after starting the app:
- By partial name:  
  http://localhost:8080/restaurants/search?name=mc
- By rating + distance:  
  http://localhost:8080/restaurants/search?customerRating=4&distance=2
- By cuisine (partial):  
  http://localhost:8080/restaurants/search?cuisineName=bur
- Multiple criteria:  
  http://localhost:8080/restaurants/search?customerRating=4&distance=2&price=12&cuisineName=burger

### cURL example
```bash
curl "http://localhost:8080/restaurants/search?name=mc"
```

## Errors
No criteria → **400 Bad Request** (ProblemDetail JSON).
