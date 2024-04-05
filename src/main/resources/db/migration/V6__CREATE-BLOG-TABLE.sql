CREATE SEQUENCE blog_seq;

CREATE TABLE IF NOT EXISTS blog (
    post_id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    author_id UUID NOT NULL,
    author_user_tag VARCHAR(10) NOT NULL,
    author_name VARCHAR(90) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    post_category VARCHAR(255) NOT NULL,
    post_content TEXT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(user_id),
    FOREIGN KEY (author_user_tag) REFERENCES users(user_tag)
);