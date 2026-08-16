# Payment & Notification Service

The `payment-service` is a Spring Boot microservice responsible for processing mocked payment transactions, generating PDF invoices, and dispatching notifications (Email & SMS).

## Features
- **API Key Security**: Endpoints are secured via a custom HTTP Header `X-API-KEY`.
- **Payment Processing**: Simulates payment processing (Credit Card, Bank Transfer) and generates UUIDs.
- **PDF Generation**: Dynamically creates a payment invoice using OpenPDF.
- **Notification Simulation**: Dispatches mock emails utilizing `JavaMailSender` and logs SMS dispatch.
- **Swagger Documentation**: Self-documenting OpenAPI specs.

## Running Locally

Run via Maven:
```bash
mvn clean spring-boot:run
```

By default it will run on port `8085` using an in-memory `H2` database.

## Running via Docker

Build the Docker image:
```bash
docker build -t estatehub/payment-service .
```

Run the container:
```bash
docker run -p 8085:8085 -e SPRING_PROFILES_ACTIVE=docker -e PAYMENT_API_KEY=your-secret-key estatehub/payment-service
```

## Security / Testing
To test the API, you must include the header:
```
X-API-KEY: estatehub-payment-secret-2026
```

Swagger UI is accessible without authentication at: http://localhost:8085/swagger-ui.html
