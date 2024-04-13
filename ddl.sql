SET client_encoding = 'UTF8';
ALTER SCHEMA PUBLIC OWNER TO dbaccess;

DROP TABLE IF EXISTS public.customers;

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100)
);

DROP TABLE IF EXISTS public.products;

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL(10, 2)
);

DROP TABLE IF EXISTS public.orders;

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    date TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

DROP TABLE IF EXISTS public.stocks;

CREATE TABLE stocks (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Insert sample data into the customers table
INSERT INTO customers (first_name, last_name, email) VALUES
    ('John', 'Doe', 'john@example.com'),
    ('Alice', 'Smith', 'alice@example.com'),
    ('Bob', 'Johnson', 'bob@example.com'),
    ('Emma', 'Brown', 'emma@example.com'),
    ('Michael', 'Wilson', 'michael@example.com'),
    ('Sophia', 'Martinez', 'sophia@example.com'),
    ('William', 'Anderson', 'william@example.com'),
    ('Olivia', 'Taylor', 'olivia@example.com'),
    ('James', 'Thomas', 'james@example.com'),
    ('Charlotte', 'Moore', 'charlotte@example.com');

-- Insert sample data into the products table
INSERT INTO products (name, description, price) VALUES
    ('Laptop', 'Description for Product 1', 10.99),
    ('TV', 'Description for Product 2', 15.49),
    ('Smartphone', 'Description for Product 3', 8.99),
    ('Mouse', 'Description for Product 4', 12.99),
    ('Watch', 'Description for Product 5', 19.99);

-- Initialize the stocks table with 100 units of each product
INSERT INTO stocks (product_id, stock)
SELECT id, 1000 FROM products;

ALTER TABLE PUBLIC.customers OWNER TO dbaccess;
ALTER TABLE PUBLIC.orders OWNER TO dbaccess;
ALTER TABLE PUBLIC.products OWNER TO dbaccess;
ALTER TABLE PUBLIC.stock OWNER TO dbaccess;