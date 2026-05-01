# Restaurant POS System (餐厅收银点餐系统)

A restaurant point-of-sale system built with Spring Boot and Vue 3.

## Tech Stack

- **Backend**: Spring Boot 3.2.5, MyBatis-Plus 3.5.5, MySQL, Java 17
- **Frontend**: Vue 3.5, Vite 8, Axios, Vue Router 4

## Prerequisites

- Java 17+
- Node.js 18+
- MySQL 8.0+

## Getting Started

### Database Setup

Create a MySQL database named `dianca_db` and update `backend/src/main/resources/application.yml` with your credentials.

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Server runs on http://localhost:8080

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Dev server runs on http://localhost:5173

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/menu/list` | Get full menu (categories + dishes) |
| GET | `/api/menu/init` | Initialize test data |
| POST | `/api/orders/submit` | Submit an order |
| GET | `/api/users/list` | Get all users |
