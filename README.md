# estatehub-estate-platform

A microservices-based real estate management system developed using Spring Boot.

## Team Members

- Member 1 – API Gateway and Authentication Service
- Member 2 – 
- Member 3 – 
- Member 4 – 
- Member 5 – 

## Microservices

- API Gateway
- Auth Service
- Property Service
- Agent Service
- Booking Service
- Payment Service

## Member 1– API Gateway and Authentication Service

**Index Number:** ITBIN-2313-0049

### Responsibilities

- Developed the Authentication Microservice using Spring Boot.
- Implemented user registration functionality.
- Created the User entity and JPA repository.
- Implemented the authentication service layer.
- Created authentication REST API endpoints.
- Added DTOs for authentication requests.
- Integrated Spring Data JPA for database access.
- Configured MySQL database connectivity.
- Structured the service using Controller, Service, Repository, DTO, and Model layers.

### Authentication API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |

### Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Lombok

## Member 1 – Authentication Service

**Index Number:** ITBIN-2313-0049

### Responsibilities
- Developed the Authentication Microservice using Spring Boot.
- Implemented user registration functionality.
- Created the User entity and JPA repository.
- Implemented authentication service layer.
- Created REST API endpoints for authentication.
- Integrated MySQL database support.
- Added request DTOs for authentication.
- Structured the service using Controller, Service, Repository, DTO, and Model layers.

### Authentication API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |

### Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Lombok

### Project Structure

```text
auth-service/
├── controller/
│   └── AuthController.java
├── dto/
│   ├── LoginRequest.java
│   └── RegisterRequest.java
├── model/
│   └── User.java
├── repository/
│   └── UserRepository.java
└── service/
    └── AuthService.java