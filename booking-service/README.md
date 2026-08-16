# booking-service

The `booking-service` handles all client reservations, property visits, and direct customer inquiries on the `estatehub-estate-platform`.

## Technologies Used
- Java 17
- Spring Boot 3.x
- Spring Web, Spring Data JPA, Spring Security
- MySQL & H2
- OpenAPI 3 (Swagger)
- Lombok
- Docker

## Features
- Provides REST APIs for CRUD operations on property visits (Bookings).
- Stores property-related messages / queries (Inquiries).
- Enforced API-Key authentication securing sensitive operational endpoints.
- Fully documented via integrated OpenAPI/Swagger specifications.

## Running Locally

By default, the application runs on port `8084` using an in-memory `H2` database for local development.

```bash
mvn clean spring-boot:run
```

## Running with Docker

You can build and run the service via Docker using the provided `docker` Spring profile which connects to a MySQL container.

```bash
docker build -t estatehub/booking-service .
docker run -p 8084:8084 -e SPRING_PROFILES_ACTIVE=docker -e BOOKING_API_KEY=your-secret-key estatehub/booking-service
```

## Security Mechanism
All service endpoints mapping `/api/v1/**` require the `X-API-KEY` HTTP header. 
Default key for local dev: `estatehub-booking-secret-2026`

## Documentation Reference
Swagger UI is accessible completely without authentication at `http://localhost:8084/swagger-ui.html`.
