# ATM Simulation System
---

## 📌 Project Overview

The **ATM Simulation System** is a Spring Boot backend project that simulates ATM operations like account login, balance inquiry, deposits, withdrawals, and viewing transaction history. It focuses on clean architecture, layered design, and REST API development best practices.

**I have followed the Best Practice of Spring Boot in this Project**   
---

## 🚀 Features

* User login using account number and PIN
* Balance inquiry endpoint
* Deposit and withdraw functionality with validation
* Transaction history endpoint
* Clean separation of controller, service, repository layers
* Proper exception handling and response formatting

---

## 🧰 Tech Stack

* Java 17+
* Spring Boot (Web, JPA)
* Hibernate ORM
* H2 / MySQL
* Maven
* Lombok

---

## 📆 Folder Structure

```
com.connectme.atm
├── controller          // REST API controllers
├── service             // Interfaces and implementations
├── model               // JPA entity classes
├── dto                 // Request/response models
├── repository          // JPA repositories
├── exception           // Custom exception classes
└── config              // Config classes (if needed)
```

---

## ⚙️ How to Run

1. Clone the project

```bash
git clone https://github.com/yourusername/atm-simulation-system.git
cd atm-simulation-system
```

2. Configure `application.properties` (for H2 or MySQL):

```properties
spring.datasource.url=jdbc:h2:mem:atmdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```

3. Run the app

```bash
./mvnw spring-boot:run
```

API Base URL: `http://localhost:8080/api/atm/`

---

## 🔧 Sample APIs

### Login

```http
POST /api/atm/login
{
  "accountNumber": "12345678",
  "pin": "1234"
}
```

### Check Balance

```http
GET /api/atm/balance/12345678
```

### Withdraw Money

```http
POST /api/atm/withdraw
{
  "accountNumber": "12345678",
  "amount": 500
}
```

### Deposit Money

```http
POST /api/atm/deposit
{
  "accountNumber": "12345678",
  "amount": 1000
}
```

### Transaction History

```http
GET /api/atm/transactions/12345678
```

---

## 🤝 Contributing

Feel free to fork this project and raise pull requests. For any suggestions, feel free to open issues.

---

## 📄 License

This project is fully functional for use and Learn.
---

## 📱 Contact

Created by **\[Harshit Varshney]** — feel free to reach out on \[harshitvarshneyv2@gmail.com]
