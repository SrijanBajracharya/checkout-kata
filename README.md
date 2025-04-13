# Checkout-kata

## Overview
Checkout Kata is a prototype project designed to simulate a supermarket checkout process. 
The system calculates the total price of a collection of items, applying relevant special offers provided by the supermarket. 
The project consists of two main endpoints:

1. Create Item with Offer: Allows the creation of items along with their associated offers.

2. Checkout: Calculates the cost of items, applying offers and discounts as necessary.
---

## Technology

- **Spring Boot 3.4.0**: For rapid development.
- **Java 21**: Leveraging the latest Java features.
- **Flyway DB**: For database migrations.
- **Testcontainers**: Simplifying integration testing.
- **Docker**: Simplified deployment and containerization.
- **mysql**: The relational database used for persisting data.

---

## Prerequisites

Ensure you have the following installed on your system:

- **Java 21**
- **Maven 3.9+**
- **Docker**

---

## Getting Started

### Clone the Repository
```bash
git clone <https://github.com/SrijanBajracharya/checkout-kata>
cd checkout-kata
```

### Build the Application

Compile and package the application:
```bash
mvn clean install
```

### Running the Application
Start the application:
```bash
mvn spring-boot:run
```

### Running the application Docker Container

You can also run the application in a Docker container using Docker Compose. To do so, run:
```bash
docker-compose up
```

Access the application on:
- **http://localhost:8080**

---

### Access the Application

Once running, the application is available at:
- **Web**: `http://localhost:8080`

---

## Database Setup

This project uses Flyway for managing database migrations. 
To configure your database connection, update the `application.yaml` file with the appropriate settings.

Here is the default configuration:
```yaml
spring.datasource.primary.jdbc-url: jdbc:mysql://localhost:3306/checkout_kata
spring.datasource.primary.username: root
spring.datasource.primary.password: root
```

---

## Key Dependencies

- **Spring Boot Starter**: Provides the core features and auto-configuration for the project.
- **Flyway DB**: For versioned migrations.
- **Hibernate**: For ORM and database interactions.
- **Testcontainers**: For setting up isolated test environments using Docker containers.

---

## APIs

### 1. Create Item with Offer:<br>
Endpoint:
```
POST request: http://localhost:8080/api/v1/items
```
```
{
    "name": "Peach",
    "unitPrice": 60,
    "offer": {
        "quantity": 2,
        "discountPrice": 45
    }
}
```
```
{
    "name": "Kiwi",
    "unitPrice": 60
}
```

### 2. Checkout <br>
Endpoint:
```
POST: http://localhost:8080/api/v1/checkout
```

```
{
    "items": [
        {
            "itemName": "Apple",
            "quantity": 1

        },
        {
            "itemName": "Banana",
            "quantity": 1

        },
        {
            "itemName": "Peach",
            "quantity": 1

        },
        {
            "itemName": "Kiwi",
            "quantity": 1

        },
        {
            "itemName": "Apple",
            "quantity": 4

        }
    ]
}
```