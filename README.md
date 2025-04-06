# Gamified Habit Tracker

![Java](https://img.shields.io/badge/Java-21-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-green) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue) ![MinIO](https://img.shields.io/badge/MinIO-latest-orange) ![License](https://img.shields.io/badge/License-MIT-yellow)

A gamified habit-tracking application built with Spring Boot. Track your habits, earn XP, unlock achievements, and level up while staying motivated! This project features user authentication, file uploads for profile images, and email verification, all integrated with PostgreSQL and MinIO storage.

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Prerequisites](#prerequisites)
5. [Setup Instructions](#setup-instructions)
6. [Running the Application](#running-the-application)
7. [Database Schema](#database-schema)
8. [API Endpoints](#api-endpoints)
9. [Configuration](#configuration)
10. [Contributing](#contributing)
11. [License](#license)

---

## Project Overview
The Gamified Habit Tracker is a RESTful Spring Boot application designed to make habit tracking fun and engaging. Users can create habits, log their progress, earn experience points (XP), and unlock achievements as they improve their routines. The app includes secure user authentication, file uploads for profile images, and email verification.

---

## Features
- User registration, login, and email verification with OTP
- Profile management (update/delete profiles, upload profile images)
- Habit creation, tracking, and logging with pagination
- Achievement system based on XP and habit milestones
- File storage using MinIO for profile images
- RESTful API with JWT-based authentication

---

## Technologies Used
- **Java**: 21
- **Spring Boot**: 3.4.4
- **Spring Data MyBatis**: For PostgreSQL integration
- **Spring Security**: JWT-based authentication
- **PostgreSQL**: Relational database
- **MinIO**: Object storage for files
- **Maven**: Dependency management
- **Lombok**: To reduce boilerplate code
- **Docker**: For MinIO containerization

---

## Prerequisites
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [PostgreSQL 16](https://www.postgresql.org/download/)
- [Docker](https://www.docker.com/get-started) (for MinIO)
- [Git](https://git-scm.com/downloads) (optional)

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/gamified-habit-tracker.git
cd gamified-habit-tracker
```

### 2. Configure Environment Variables
Create a .env file in the root directory and populate it with the following:
```bash
POSTGRES_USER=your_postgres_user
POSTGRES_PASSWORD=your_postgres_password
POSTGRES_DB=spring_mini_project
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_ID=your_email@gmail.com
EMAIL_PASSWORD=your_app_password
VERIFY_EMAIL_HOST=http://localhost:9090
JWT_SECRET=your_secret_key
JWT_EXPIRATION=3600
```

### 3. Set Up PostgreSQL
Install PostgreSQL and create a database:
```bash
CREATE DATABASE spring_mini_project;
```

### 4. Start MinIO with Docker
Run the MinIO service using the provided compose.yml:

```bash
docker-compose -f compose.yml up -d
```
Access the MinIO console at http://localhost:9001 (user: admin, password: Password@123!).

### 5. Install Dependencies
```bash
mvn clean install
```

### Running the Application
Start the Spring Boot application:
```bash
mvn spring-boot:run
```
The app will run on http://localhost:9090.


