CREATE SEQUENCE professioanls_seq;

CREATE TABLE IF NOT EXISTS professionals (
    user_id UUID PRIMARY KEY,
    professional_type TEXT,
    instagram_url VARCHAR(255),
    skills BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (skills) REFERENCES filters(filter_id)
);