# Hostel Mess Food Waste Management System

A backend system to track, reduce, and analyze food waste in hostel mess operations. Built as a final-year academic project.

## Problem Statement

Hostel mess kitchens often prepare food without knowing how many students will actually eat, leading to significant food waste and financial loss. This system lets staff plan meals based on student opt-outs, log waste after each meal, and generate cost/quantity reports to identify waste patterns over time.

## Features

- **Role-based authentication** (Student, Staff, Admin) using JWT
- **Daily menu management** — staff can add menu items for any date/meal
- **Meal opt-out** — students can opt out of meals in advance, helping staff estimate cooking quantity
- **Waste logging** — staff logs prepared vs. consumed quantity after each meal; wasted quantity is auto-calculated
- **Reports** — total waste (kg) and estimated cost lost over a date range
- **Student feedback** — students rate meals and leave comments, with average rating per date

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.3.4, Spring Security, Spring Data JPA |
| Databases | MySQL (users, menu, waste logs), MongoDB (feedback) |
| Authentication | JWT (JJWT library) |
| API Docs | Swagger / OpenAPI |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |

## Architecture

Follows a layered architecture:- **Controller** — handles HTTP requests/responses
- **Service** — business logic (e.g., password hashing, waste calculation)
- **Repository** — database access via Spring Data JPA / MongoDB
- **Entity** — data models mapped to database tables/collections

## API Endpoints

### Auth
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user (Student/Staff/Admin) |
| POST | `/api/auth/login` | Login and receive a JWT token |

### Menu
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/menu/add` | Add a menu item for a date |
| GET | `/api/menu/date/{date}` | View menu for a specific date |
| POST | `/api/menu/optout` | Student opts out of a meal |
| GET | `/api/menu/optout-count` | Get opt-out count for a meal |

### Waste
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/waste/log` | Log prepared/consumed quantity for a meal |
| GET | `/api/waste/date/{date}` | View waste logs for a date |
| GET | `/api/waste/report` | Get waste/cost report between two dates |

### Feedback
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/feedback/submit` | Submit meal feedback and rating |
| GET | `/api/feedback/date/{date}` | View feedback and average rating for a date |

## Setup Instructions

### Prerequisites
- Java 17
- Maven
- MySQL 8 (running on port 3306)
- MongoDB (running on port 27017)

### Steps
1. Clone the repository:2. Update `src/main/resources/application.properties` with your own MySQL username/password.
3. Run the application:   Or run `MessWasteApplication.java` directly from your IDE.
4. Open Swagger UI to test the APIs:
## Database Design

- **MySQL tables:** `users`, `menu_items`, `meal_optouts`, `waste_logs`
- **MongoDB collection:** `feedback`

Uses MySQL for structured, relational data (users, menu, waste) and MongoDB for flexible, high-write feedback data — demonstrating polyglot persistence.

## Planned Features (Future Scope)

- Quantity prediction based on historical opt-out and waste data
- NGO/food bank alert system for surplus food donation
- Frontend dashboard with charts for waste trends
- Email notifications for meal reminders

## Author

Built by [Bokade Pratik].