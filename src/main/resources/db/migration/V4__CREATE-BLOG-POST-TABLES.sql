CREATE SEQUENCE IF NOT EXISTS blog_posts_seq;

CREATE TABLE IF NOT EXISTS blog_posts (
    blog_post_id BIGINT NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    post_created_date TIMESTAMP NOT NULL,
    post_categories INT,
    content TEXT NOT NULL
);