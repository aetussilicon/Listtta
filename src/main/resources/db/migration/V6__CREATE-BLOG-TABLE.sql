CREATE SEQUENCE blog_seq;

CREATE TABLE IF NOT EXISTS blog (
    post_id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    author UUID NOT NULL,
    created_date TIMESTAMP NOT NULL,
    post_category VARCHAR(255) NOT NULL,
    post_content TEXT NOT NULL,
    FOREIGN KEY (author) REFERENCES users(user_id)
);