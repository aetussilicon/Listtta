CREATE TABLE IF NOT EXISTS professionals (
    user_id UUID PRIMARY KEY NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    username VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    tax_number VARCHAR(20) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    state VARCHAR(15),
    city VARCHAR(30),
    district VARCHAR (50),
    postal_code VARCHAR (9),
    instagram_url VARCHAR(255)
);
