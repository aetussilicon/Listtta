CREATE TABLE IF NOT EXISTS customers (
    customer_id UUID PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    username VARCHAR(10) NOT NULL UNIQUE,
    phone_number VARCHAR(13) NOT NULL,
    tax_number VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    state VARCHAR(15),
    city VARCHAR(30),
    district VARCHAR (50),
    postal_code VARCHAR (9)
);