# agent-service

The `agent-service` is a domain microservice handling the lifecyle of Real Estate Agent profiles and aggregations of customer ratings.

## Technologies Used
- Java 17
- Spring Boot 3.x
- Spring Web, Spring Data JPA, Spring Security
- MySQL & H2
- OpenAPI 3 (Swagger)
- Lombok
- Docker

## Features
- Complete CRUD operations for Real Estate Agents.
- Ratings submission for existing agents (scale 1 to 5).
- Aggregation endpoints to fetch all ratings mapped to a specific agent.
- Complete API-Key authentication strategy implementation natively via Filters.

## Running Locally

By default, the application runs on port `8083` using an in-memory `H2` database for local development.

```bash
mvn clean spring-boot:run
```

## Running with Docker

You can build and run the service via Docker using the provided `docker` Spring profile which connects to a MySQL container.

```bash
docker build -t estatehub/agent-service .
docker run -p 8083:8083 -e SPRING_PROFILES_ACTIVE=docker -e AGENT_API_KEY=your-secret-key estatehub/agent-service
```
## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/agents` | Register a new agent |
| GET | `/api/v1/agents` | Retrieve all agents |
| GET | `/api/v1/agents/{id}` | Retrieve an agent by ID |
| PUT | `/api/v1/agents/{id}` | Update agent details |
| DELETE | `/api/v1/agents/{id}` | Delete an agent |
| POST | `/api/v1/agents/{id}/ratings` | Add a rating for an agent |
| GET | `/api/v1/agents/{id}/ratings` | Retrieve agent ratings |

## Security Mechanism
All service endpoints requiring authenticated CRUD operations (`/api/v1/**`) rely on the `X-API-KEY` header. 
Default defined for local dev: `estatehub-agent-secret-2026`

## Documentation Reference
Swagger UI is accessible publicly at `http://localhost:8083/swagger-ui.html`.
