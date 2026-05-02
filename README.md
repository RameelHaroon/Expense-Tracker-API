Expense Tracker API
 
A RESTful backend API for tracking personal expenses, built with Spring Boot and secured with Spring Security and JWT.
 
 
Tech Stack
 
Java 17+, Spring Boot 3.x, Spring Security, JWT (HS256), Spring Data JPA, BCrypt password hashing, PostgreSQL with flyway, and Docker.
 
 
Authentication Flow
 
Register — POST /auth/register
Send name, email, and password. The password is hashed with BCrypt and saved to the database. A signed JWT is returned immediately so the user is logged in right after signing up.
 
Login — POST /auth/login
Send email and password. Spring Security compares the provided password against the stored BCrypt hash. On success a JWT is returned. On failure a 401 Unauthorized is returned.
 
Authenticated Requests
All protected endpoints require the token in the Authorization header as a Bearer token. The JWT filter validates the token on every request, loads the user from the database, and sets the authentication in the security context.
 
Getting Started
 
Clone the repository, configure your database connection and JWT secret in application.properties, then run the application with ./mvnw spring-boot:run. The API will be available at http://localhost:8080.
