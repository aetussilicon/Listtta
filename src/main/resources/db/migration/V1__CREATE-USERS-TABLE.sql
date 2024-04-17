CREATE TABLE IF NOT EXISTS users (
   user_id UUID PRIMARY KEY UNIQUE NOT NULL,
   full_name VARCHAR(90) NOT NULL,
   user_tag VARCHAR(20) NOT NULL UNIQUE,
   profile_picture BYTEA,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role TEXT NOT NULL,
   tax_number VARCHAR(20) NOT NULL,
   phone_number VARCHAR(20) NOT NULL,
   created_date TIMESTAMP
);