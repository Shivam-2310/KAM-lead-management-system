# **KAM Lead Management System**

The **KAM (Key Account Manager) Lead Management System** is a comprehensive Spring Boot application designed to streamline the process of managing restaurant leads and interactions for Key Account Managers. This system provides a robust set of features to track restaurants, manage contacts, plan calls, record interactions, and analyze performance.

---

## **Features**

- **Restaurant Management:** Add, update, and retrieve restaurant information.
- **Contact Management:** Maintain a database of restaurant contacts.
- **Call Planning:** Schedule and manage calls with restaurants.
- **Interaction Tracking:** Record and retrieve various types of interactions (calls, orders, meetings, emails) with restaurants.
- **Performance Analysis:** Generate insights on restaurant performance based on order values.
- **JWT Authentication:** Secure API endpoints using JSON Web Tokens.

---

## **Technology Stack**

- **Java:** 17
- **Spring Boot:** 3.4.1
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT:** For authentication
- **Ollama AI:** Integration for performance analysis

---

## **Project Structure**

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── shivam
│   │           └── lead_management_system
│   │               ├── config
│   │               ├── controller
│   │               ├── dto
│   │               ├── entity
│   │               ├── exception
│   │               ├── repository
│   │               ├── security
│   │               └── service
│   └── resources
│       ├── application.properties
│       ├── banner.txt
│       └── schema.sql
└── test
    └── java
        └── com
            └── shivam
                └── lead_management_system
```

---

## **Key Components**

### **Controllers**
- **AuthenticationController:** Handles user authentication and JWT token generation.
- **CallPlanningController:** Manages call planning operations.
- **ContactController:** Handles contact-related operations.
- **InteractionController:** Manages interaction records.
- **PerformanceController:** Generates performance analysis.
- **RestaurantController:** Handles restaurant-related operations.

### **Services**
- **CallPlanningService:** Business logic for call planning.
- **ContactService:** Manages contact operations.
- **InteractionService:** Handles interaction-related operations.
- **KAMDetailsService:** Implements `UserDetailsService` for authentication.
- **OllamaService:** Integrates with Ollama AI for analysis generation.
- **PerformanceService:** Generates performance insights.
- **RestaurantService:** Manages restaurant-related operations.

### **Entities**
- **CallPlanning:** Represents call planning information.
- **Contact:** Represents a restaurant contact.
- **Interaction:** Represents an interaction with a restaurant.
- **KAM:** Represents a Key Account Manager user.
- **Restaurant:** Represents a restaurant in the system.

---

## **Setup and Installation**

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Shivam-2310/KAM-lead-management-system.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd kam-lead-management-system
   ```

3. **Set up the PostgreSQL database and update the `application.properties` file with your database credentials.**

4. **Set the following environment variables:**
   ```bash
   export JDBC_DATABASE_URL=<your_database_url>
   export DATABASE_USERNAME=<your_database_username>
   export DATABASE_PASSWORD=<your_database_password>
   export JWT_SECRET=<your_jwt_secret>
   export JWT_EXPIRATION=<jwt_expiration_time>
   export OLLAMA_BASE_URL=<ollama_base_url>
   export OLLAMA_MODEL=<ollama_model_name>
   ```

5. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

6. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## **API Endpoints**

- **POST** `/auth/login`: Authenticate and receive a JWT token.
- **GET/POST/PUT/DELETE** `/api/restaurants`: CRUD operations for restaurants.
- **GET/POST/PUT/DELETE** `/api/contacts`: Manage restaurant contacts.
- **GET/POST/PUT/DELETE** `/api/call-planning`: Manage call planning.
- **GET/POST/PUT/DELETE** `/api/interactions`: Record and retrieve interactions.
- **GET** `/api/performance/analysis`: Generate performance analysis.

---

## **Security**

The application uses Spring Security with JWT for authentication. All endpoints, except for `/auth/login`, require a valid JWT token in the `Authorization` header.

---

## **Performance Analysis**

The system integrates with Ollama AI to generate performance insights based on restaurant order data. This feature provides valuable analytics for Key Account Managers to assess and improve their strategies.

---

## **Contributing**

Contributions are welcome! Please feel free to submit a Pull Request.


