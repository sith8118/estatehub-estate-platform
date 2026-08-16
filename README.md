# estatehub-estate-platform

A microservices-based real estate management system developed using Spring Boot.

## Team Members

- Member 1 – API Gateway and Authentication Service
- Member 2 – 
- Member 3 – 
- Member 4 – 
- Member 5 – Payment and Notification Service

## Microservices

- API Gateway
- Auth Service
- Property Service
- Agent Service
- Booking Service
- Payment Service

## Member 1 – API Gateway and Authentication Service

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
```

## Member 5 – Payment and Notification Service

**Index Number:** [Your Index Number]

### Responsibilities

- Developed the Payment Microservice using Spring Boot.
- Implemented transactional payment processing.
- Generated dynamic payment invoices using OpenPDF.
- Implemented mock email and SMS notification dispatching.
- Secured service endpoints via custom API Key authentication.
- Configured multi-stage Dockerfile for containerized deployment.
- Structured the service using Controller, Service, Repository, DTO, and Security layers.

### Payment API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/payments/process` | Process a payment and generate invoice |

### Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL / H2
- OpenPDF
- JavaMailSender
- OpenAPI/Swagger
- Lombok
- Docker

### Project Structure

```text
payment-service/
├── controller/
│   └── PaymentController.java
├── dto/
│   ├── PaymentRequest.java
│   └── PaymentResponse.java
├── model/
│   └── PaymentTransaction.java
├── repository/
│   └── PaymentRepository.java
├── security/
│   └── ApiKeyAuthFilter.java
└── service/
    ├── PaymentService.java
    └── NotificationService.java
```