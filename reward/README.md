# Reward Points System

This application calculates reward points based on customer transactions. The reward system works as follows:
- 2 points for every dollar spent over $100 in a single transaction.
- 1 point for every dollar spent over $50 in a single transaction.

## Features:
- RESTful API to get customer rewards.
- Monthly and total reward points are calculated based on transactions.

### Technologies Used:
- Spring Boot
- Java
- JUnit 5 for testing
- SLF4J for logging

### How to Run:
1. Clone the repository.
2. Build the project using Maven: `mvn clean install`.
3. Run the application: `mvn spring-boot:run`.

### API Endpoint:
- `GET /api/rewards/{customerId}`: Fetches the rewards for a customer.
