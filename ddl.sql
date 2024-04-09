SET client_encoding = 'UTF8';
ALTER SCHEMA PUBLIC OWNER TO dbaccess;

DROP TABLE IF EXISTS public.customers;

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100)
);

DROP TABLE IF EXISTS public.orders;

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INT,
    order_date TIMESTAMP,
    total_amount DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

DROP TABLE IF EXISTS public.products;

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL(10, 2)
);

DROP TABLE IF EXISTS public.order_items;

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

DROP TABLE IF EXISTS public.reviews;

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    product_id INT,
    customer_id INT,
    rating INT,
    comment TEXT,
    date TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

ALTER TABLE PUBLIC.customers OWNER TO dbaccess;
ALTER TABLE PUBLIC.orders OWNER TO dbaccess;
ALTER TABLE PUBLIC.products OWNER TO dbaccess;
ALTER TABLE PUBLIC.order_items OWNER TO dbaccess;
ALTER TABLE PUBLIC.reviews OWNER TO dbaccess;