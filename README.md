DBTransactions - Concurrency Handling Demo
=====================

This project is a demonstration of different ways to handle concurrency in a distributed system. It uses Spring Boot with Java 17 and PostgreSQL as the database.

The main objective is to simulate an e-commerce scenario where multiple requests are received to update stock values. The application emulates this by receiving N requests and then
decreasing the value from the stock.

## Concurrency Handling Solutions

0. **No Solution**: In this case, we simply decrease the stock value without any concurrency handling.
1. **Pessimistic Locking (FOR UPDATE)**: This solution uses PostgreSQL's FOR UPDATE query to lock the row while updating the stock value.
2. **Pessimistic Locking (pg_advisory_xact_lock)**: This solution uses pg_advisory_xact_lock from PostgreSQL to achieve pessimistic locking, ensuring that only one transaction can
   update the stock value at a time.
3. **Optimistic Locking**: In this case, we use an optimistic approach by adding a WHERE clause when decreasing the stock value. If another transaction updates the stock value in
   between, the optimistic lock will detect it and roll back the operation.

### Infrastructure
To run this project, you'll need to have Docker installed on your machine. You can then spin up the infrastructure using `docker-compose up -d`.

The `ddl.sql` file, located in the root of this project, contains the database schema to create the tables and rows for the
application. This file is used to initialize the PostgreSQL database when the container starts.

**Features**

* Demonstrates four different concurrency handling solutions using Java 17, Spring Boot, and PostgreSQL.
* Allows for experimentation with different concurrency scenarios and their effects on the system.

I hope this README.md file accurately represents your application! Let me know if you have any further requests or changes.