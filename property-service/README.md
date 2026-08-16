# property-service

The `property-service` is a microservice for the estatehub-estate-platform, responsible for comprehensive property management functionality, including CRUD operations and advanced search filtering.

## Technologies Used
- Java 17
- Spring Boot 3.x
- Spring Web, Spring Data JPA, Spring Security
- MySQL & H2
- OpenAPI 3 (Swagger)
- Lombok
- Docker

## Features
- Manages real estate properties (Creation, Modification, Retrieval, and Deletion).
- Advanced search functionality based on location, price range, bedrooms, and property type.
- Enforced API-Key authentication for data-modifying endpoints.
- Fully documented via integrated OpenAPI/Swagger specifications.

## Running Locally

By default, the application runs on port `8082` using an in-memory `H2` database for local development.

```bash
mvn clean spring-boot:run
```

## Running with Docker

You can build and run the service via Docker using the provided `docker` Spring profile which connects to a MySQL container, or standard variables.

```bash
docker build -t estatehub/property-service .
docker run -p 8082:8082 -e SPRING_PROFILES_ACTIVE=docker -e PROPERTY_API_KEY=your-secret-key estatehub/property-service
```

## Security Mechanism
Most service endpoints (except Swagger UI) require the `X-API-KEY` HTTP header. 
In a deployed environment, this key is provided via API Gateway or secured inter-service communication.
Default key for local dev: `estatehub-property-secret-2026`

## Documentation Reference
Swagger UI can be explored without authentication at `http://localhost:8082/swagger-ui.html`.
