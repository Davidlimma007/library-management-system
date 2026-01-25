# 📚 Library Management System

A RESTful API for managing a library system, built with **Java** and **Spring Boot**, focusing on real-world business rules, security, and clean architecture.

This project is designed for **learning purposes and professional portfolio**, simulating a real library environment with users, employees, livro loans, fines, and access control.

---

## 🎯 Project Goals

- Apply real business rules (not just CRUD)
- Practice Spring Boot with a professional architecture
- Implement authentication and authorization
- Use a relational database (PostgreSQL)
- Build a portfolio-ready backend application

---

## 🧠 Business Rules

- A usuario can borrow **up to 2 books**
- Loan period is **15 days**
- A emprestimo can be **renewed only once** for another 15 days
- Users with **late returns are blacklisted**
- Blacklisted users **cannot borrow books**
- A new emprestimo is allowed only if at least one livro has been returned
- Late returns generate a fine:
  - Base fine: **R$ 20.00**
  - Daily interest: **R$ 2.00 per late day**
- Every emprestimo is handled by an **funcionario**
- All loans and returns are stored for history tracking

---

## 🧱 Domain Model (High Level)

Main entities:

- User (library usuario or funcionario)
- Book
- Category
- Loan
- Fine

> Note: Employees are users with a specific role.

---

## 🔐 Security

- Authentication using **Spring Security**
- Authorization based on **roles**
- JWT-based authentication (planned)

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- Spring Security
- PostgreSQL
- Maven
- Lombok
- Bean Validation
- Postman (API testing)

---

## 🚀 Project Status

🚧 **In development**

Current stage:
- Project setup
- Repository and documentation
- Spring Boot initial configuration

---

## 📌 Future Improvements

- JWT authentication implementation
- API documentation with Swagger/OpenAPI
- Unit and integration tests
- Frontend application
- Docker support

---

## 👤 Author

**David Lima**  
Backend Developer (Java & Spring Boot)

