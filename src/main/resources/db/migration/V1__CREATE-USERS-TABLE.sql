CREATE TABLE IF NOT EXISTS users (
   user_id UUID PRIMARY KEY UNIQUE NOT NULL,
   user_tag VARCHAR(20) UNIQUE,
   puid VARCHAR(10) NOT NULL UNIQUE,
   full_name VARCHAR(90),
   user_gender TEXT,
--   profile_picture BYTEA,
   created_date TIMESTAMP NOT NULL,
   email VARCHAR(255) UNIQUE,
   password VARCHAR(255) NOT NULL,
   tax_number VARCHAR(20) NOT NULL UNIQUE,
   phone_number VARCHAR(20) UNIQUE,
   whatsapp_contact VARCHAR(20) UNIQUE,
   role TEXT NOT NULL
);