CREATE TABLE IF NOT EXISTS users (
   user_id UUID PRIMARY KEY UNIQUE NOT NULL,
   full_name VARCHAR(90) NOT NULL,
   username VARCHAR(10) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role TEXT NOT NULL,
   tax_number VARCHAR(20) NOT NULL,
   phone_number VARCHAR(20) NOT NULL,
   state VARCHAR(20),
   city VARCHAR(30),
   district VARCHAR(50),
   postal_code VARCHAR(9)
);