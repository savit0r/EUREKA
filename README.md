# Eureka Microservices Architecture

A lightweight Spring Boot microservices demonstration showcasing Service Discovery and Registration using **Spring Cloud Netflix Eureka**.

---

## 📌 Architecture Overview

This project consists of two core microservices:

1. **`eurekaserver`** (Service Registry)
   - Acts as the central Service Discovery Server powered by **Spring Cloud Netflix Eureka Server**.
   - Runs on port **`8761`**.
   - Provides a web dashboard at `http://localhost:8761` to monitor registered services.

2. **`user-service`** (Microservice Client)
   - A sample REST microservice powered by **Spring Web MVC** and **Eureka Client**.
   - Runs on port **`8081`**.
   - Automatically registers itself with `eurekaserver` upon startup.
   - Exposes a sample API endpoint for user data.

---

## 🛠️ Tech Stack

- **Java**: Version 17
- **Framework**: Spring Boot (v4.1.1 / 3.x compatible)
- **Cloud Infrastructure**: Spring Cloud Netflix Eureka (2025.1.3)
- **Build Tool**: Apache Maven (Wrapper included)

---

## 📁 Repository Structure

```text
EUREKA/
├── eurekaserver/        # Eureka Service Registry Application (Port 8761)
│   ├── src/
│   └── pom.xml
├── user-service/        # User Microservice (Port 8081)
│   ├── src/
│   └── pom.xml
└── README.md            # Project documentation
```

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK 17)** or higher
- **Maven** (optional, `./mvnw` wrappers are provided)

---

### Step-by-Step Run Instructions

#### 1️⃣ Start the Eureka Server

First, start the Eureka Service Registry so client services can discover and register with it.

```bash
cd eurekaserver
./mvnw spring-boot:run
```

Once started, open your browser and navigate to:
👉 **[http://localhost:8761](http://localhost:8761)** to view the Eureka Dashboard.

---

#### 2️⃣ Start the User Service

In a new terminal window, start the User Service:

```bash
cd user-service
./mvnw spring-boot:run
```

Once running, refresh the Eureka Dashboard at `http://localhost:8761`. You should see `USER-SERVICE` registered under **Instances currently registered with Eureka**.

---

## 📡 API Endpoints

### User Service (`http://localhost:8081`)

| Method | Endpoint | Description | Sample Response |
| :--- | :--- | :--- | :--- |
| `GET` | `/users` | Retrieves a list of users | `"Users returned from USER-SERVICE"` |

To test the endpoint using `curl`:

```bash
curl http://localhost:8081/users
```

---

## ⚙️ Key Configuration Details

### Eureka Server Configuration (`eurekaserver/src/main/resources/application.properties`)

```properties
spring.application.name=eureka-server
server.port=8761

# Disable self-registration for the registry server
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

eureka.instance.prefer-ip-address=true
eureka.instance.ip-address=127.0.0.1
```

### User Service Configuration (`user-service/src/main/resources/application.properties`)

```properties
spring.application.name=user-service
server.port=8081

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true
```

---

## 📄 License

This project is open-source and available for learning and reference purposes.
