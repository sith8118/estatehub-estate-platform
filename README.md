# 🏢 EstateHub - Real Estate Microservices Platform

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![React](https://img.shields.io/badge/React-18-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## 📌 Project Overview
**EstateHub** is a comprehensive, distributed system for managing real estate properties, agents, bookings, and payments. It is built as a university group project for the **Service-Oriented Computing** module, demonstrating scalable, secure, and modern microservices architecture principles.

---

## 🏗️ Architecture & Tech Stack

Our platform strictly adheres to a microservices architectural pattern, ensuring separation of concerns, scalability, and robust security.

- **Backend:** Spring Boot 3.x, Java 17, Spring Cloud Gateway, Spring Data JPA, Hibernate.
- **Frontend:** React 18, Vite, Tailwind CSS.
- **Database:** MySQL 8.0 (Containerized).
- **Orchestration:** Docker & Docker Compose.
- **Documentation:** Springdoc OpenAPI 3 (Swagger).

---

## 👥 Team Members & Service Ownership

This project was a collaborative effort by 5 team members. Each member took full ownership of a distinct domain service:

- **Member 1 (Gateway & Auth Service):** Implemented Spring Cloud API Gateway on `port 8080`, handled OAuth2/JWT authentication, CORS configuration, and global routing.
- **Member 2 (Property Service):** Developed the core property management system (`port 8082`) with advanced dynamic search filtering.
- **Member 3 (Agent Service):** Created the agent profile and rating aggregation system (`port 8083`).
- **Member 4 (Booking Service):** Implemented customer property visit reservations and inquiries logic (`port 8084`).
- **Member 5 (Payment & Notification Service):** Built the transaction processor, PDF invoice generator, and notification simulator (`port 8085`).

---

## 🛡️ Security Implementation

Security is a crucial component of our platform, enforcing a resilient **dual-layer security approach**:

1. **Frontend to Gateway:** 
   Client-facing authentication is secured via standard **JWT (JSON Web Tokens)**. Users receive a signed token upon login, which is strictly validated at the API Gateway layer.
   
2. **Inter-Service Security:** 
   Every individual backend microservice enforces a strict **API-Key Security Policy**. A custom Servlet Filter is implemented inside each service to validate the `X-API-KEY` header against configured environment secrets (e.g., `estatehub-payment-secret-2026`). Unauthorized internal or external requests bypassing the Gateway are instantly rejected with standard `401 Unauthorized` or `403 Forbidden` JSON errors.

---

## 🚀 Getting Started (Docker Deployment)

The entire microservices ecosystem is configured for seamless orchestration using Docker. 

> **Prerequisite:** Ensure **Docker Desktop** is installed and actively running on your machine.

Follow these steps to spin up the application:

### Step 1: Clean previous states
Remove any old persisting database volumes to ensure a fresh environment:
```bash
docker compose down -v
```

### Step 2: Spin up the Backend Services
Build and start the MySQL DB, API Gateway, and all 5 microservices in detached mode:
```bash
docker compose up --build -d
```

### Step 3: Run the Frontend UI
Navigate to the frontend directory, install dependencies, and launch the Vite development server:
```bash
cd frontend
npm install
npm run dev
```

---

## 📖 API Documentation

Each service generates real-time OpenAPI documentation. You can access the interactive Swagger UI directly for testing endpoints:

- **API Gateway:** `http://localhost:8080` *(Routes to all downstream services)*
- **Auth Service:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **Property Service:** [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
- **Agent Service:** [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)
- **Booking Service:** [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)
- **Payment Service:** [http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html)

---
*Created with ❤️ by the EstateHub Team for the Service-Oriented Computing module.*