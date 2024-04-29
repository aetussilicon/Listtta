CREATE SEQUENCE address_seq;

CREATE TABLE IF NOT EXISTS users_address (
    address_id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    user_id UUID NOT NULL UNIQUE,
    user_tag VARCHAR(10) NOT NULL UNIQUE,
    state VARCHAR(20) NOT NULL,
    city VARCHAR(30) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (user_tag) REFERENCES users(user_tag)
);