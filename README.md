# 🏡 EstateHub Platform

A modern, scalable real estate management system built with Spring Boot Microservices. EstateHub provides a comprehensive suite of services to manage properties, agents, bookings, and payments, all securely routed through an API Gateway.

---

## 🏗️ Architecture

The platform follows a microservices architecture, exposing endpoints through a centralized API Gateway running on `http://localhost:8080`. The ecosystem consists of 5 distinct microservices:

| Microservice | Description | Database |
| :--- | :--- | :--- |
| **Auth Service** | Acts as the gatekeeper, handling user registration and JWT-based authentication. | MySQL |
| **Property Service** | Core service managing real estate listings, details, and availability. | MongoDB |
| **Agent Service** | Manages real estate agents, their profiles, and customer ratings. | MySQL |
| **Booking Service** | Handles customer property visit reservations and deal-making. | MySQL |
| **Payment Service** | Processes transactions and generates invoices for confirmed bookings. | MySQL |

---

## 🗄️ Polyglot Persistence (Viva Highlight)

To optimize data storage based on the distinct needs of each domain, EstateHub implements **Polyglot Persistence**:

- 🐘 **MySQL (Relational):** Used by Auth, Agent, Booking, and Payment services. It ensures strict ACID compliance, data integrity, and complex transactional relationships necessary for user accounts and financial records.
- 🍃 **MongoDB (NoSQL):** Specifically chosen for the **Property Service**. It handles flexible, document-based real estate data, allowing for dynamic property attributes without rigid schema constraints.

---

## 🛠️ Prerequisites

To run and interact with the platform locally, ensure you have the following installed:
- 🐳 **Docker Desktop**
- 🚀 **Postman** (For API Testing)
- 🍃 **MongoDB Compass** (Optional: For inspecting the Property Database)
- 🐬 **MySQL Workbench** (Optional: For inspecting Relational Databases)

---

## 🚀 How to Run

1. Clean up any existing containers and volumes:
   ```bash
   docker compose down -v
   ```
2. Pull the latest changes:
   ```bash
   git pull
   ```
3. Build and spin up the entire microservices ecosystem:
   ```bash
   docker compose up --build
   ```

The **API Gateway** will be available at `http://localhost:8080`.

---

## 🎓 Viva Demonstration Flow (The 5-Step Process)

To successfully demonstrate the platform during the Viva, follow this logical workflow:

1. 🔐 **Step 1: Auth (Gatekeeper)**
   - Register a new user and Login to generate a secure **JWT Token**.
2. 🏡 **Step 2: Property (Core)**
   - Use the generated JWT to Create a new property listing (Data is saved flexibly in **MongoDB**).
3. 👔 **Step 3: Agent (Manager)**
   - Add a managing agent profile for the property (Saved in **MySQL**).
4. 📅 **Step 4: Booking (Deal Maker)**
   - Create a property visit booking for a customer (Saved in **MySQL**).
5. 💳 **Step 5: Payment (Closer)**
   - Process the payment for the confirmed booking (Saved in **MySQL**).

---

## 📬 Postman Collection

For a seamless, one-click testing experience during the Viva, a complete Postman Collection is provided in the repository:

- 📄 **File:** `EstateHub_Viva_Postman_Collection.json`
- **Features:** It includes all endpoints mapped to the API Gateway (`http://localhost:8080`), sample JSON payloads, automatic JWT extraction via test scripts, and pre-configured `X-API-KEY` security headers.

Import this file into Postman and execute the **5-Step Process** effortlessly!