# Habit Tracker REST API

A backend REST API built using Spring Boot for tracking daily habits.

## Features
- Create habits
- Mark habit completion
- Weekly progress calculation
- Pagination for habit history
- Global exception handling
- Swagger API documentation

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Swagger / OpenAPI

## API Endpoints
-POST   /api/habits
-GET    /api/habits
-POST   /api/habits/{id}/complete
-GET    /api/habits/{id}/history
-GET    /api/habits/{id}/weekly-progress
