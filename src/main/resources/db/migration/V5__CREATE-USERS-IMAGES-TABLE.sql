CREATE SEQUENCE users_images_seq;

CREATE TABLE IF NOT EXISTS users_images (
    images_id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    user_id UUID NOT NULL UNIQUE,
    user_image bytea,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);