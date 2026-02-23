# 🚗 Driver Sentiment Engine

A Spring Boot based backend system that processes rider feedback, performs sentiment analysis, maintains driver performance metrics, and generates alerts for low-performing drivers in real time.

---

## 📌 Problem Statement

Ride-sharing platforms collect feedback after each trip, but often fail to proactively detect risky or low-performing drivers.

This system:

- Accepts textual feedback from employees/riders
- Performs sentiment classification (positive / neutral / negative)
- Maintains rolling average sentiment score per driver
- Automatically triggers alerts when a driver’s performance drops below a configurable threshold

---

## 🏗 Architecture Overview

```
Controller Layer
        ↓
Service Layer
        ↓
Strategy Pattern (Sentiment Engine)
        ↓
Driver Stats Aggregation
        ↓
Alert Generation
        ↓
PostgreSQL Database
```

Additionally:

- Asynchronous feedback processing using `BlockingQueue`
- Thread-safe updates using `ConcurrentHashMap`
- Clean layered architecture
- Dependency Injection via Spring

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot 4
- Spring Data JPA
- Hibernate ORM
- PostgreSQL
- Thymeleaf (Admin UI)
- Maven
- HikariCP (Connection Pooling)

---

## 🧠 Design Patterns Used

### 1️⃣ Strategy Pattern
Used for pluggable sentiment analysis logic.

```java
public interface SentimentStrategy {
    double analyze(String text);
}
```

This allows future integration of ML-based sentiment engines without changing business logic.

---

### 2️⃣ Layered Architecture

- Controller → Handles HTTP requests
- Service → Business logic
- Repository → Database interaction
- Model → Entity classes
- Queue → Async processing

---

## 🚀 Features

✔ Submit driver feedback via web form  
✔ Rule-based sentiment analysis  
✔ Real-time average score calculation  
✔ Configurable alert threshold  
✔ Admin dashboard with driver stats  
✔ Graph visualization of performance  
✔ Thread-safe concurrent processing  
✔ PostgreSQL persistence  

---

## 🔧 Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/driverdb
spring.datasource.username=postgres
spring.datasource.password=your_password_here

alert.threshold=2.5
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ How To Run

### 1️⃣ Create PostgreSQL database

```sql
CREATE DATABASE driverdb;
```

---

### 2️⃣ Run the application

```bash
mvn spring-boot:run
```

OR run from IntelliJ.

---

### 3️⃣ Open in browser

Feedback Form:

```
http://localhost:8080/feedback-form
```

Admin Dashboard:

```
http://localhost:8080/admin/page
```

---

## 📊 System Flow

1. User submits feedback
2. Feedback enters async queue
3. Sentiment is analyzed
4. Driver stats are updated
5. Average score recalculated
6. If below threshold → Alert generated
7. Admin dashboard reflects updated data

---

## 📈 Alert Logic

Alert is generated when:

```
averageScore < alert.threshold
```

Threshold is configurable via properties file.

---

## 🔒 Thread Safety

- `BlockingQueue` ensures safe async processing
- `ConcurrentHashMap` ensures safe concurrent driver updates
- Stateless service design

---

## 📌 Future Improvements

- Replace rule-based sentiment with ML model
- Replace BlockingQueue with Kafka
- Implement exponential decay scoring
- Add Docker support
- Deploy to AWS
- Add authentication & role-based access

---

## 👨‍💻 Author

**Harvindar Singh**  
B.Tech CSE | Backend & ML Enthusiast  
Passionate about scalable backend systems and ML integration.

---

## ⭐ Project Purpose

This project demonstrates:

- System Design fundamentals
- Backend architecture skills
- Real-time processing concepts
- Clean code practices
- Production-style layering
