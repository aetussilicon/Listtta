CREATE SEQUENCE address_seq;

CREATE TABLE IF NOT EXISTS users_address (
    address_id BIGINT NOT NULL UNIQUE,
    state VARCHAR(20) NOT NULL,
    city VARCHAR(30) NOT NULL,
    district VARCHAR(90) NOT NULL,
    postal_code VARCHAR(9) NOT NULL
);