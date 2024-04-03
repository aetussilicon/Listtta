CREATE SEQUENCE professionals_seq;

CREATE TABLE IF NOT EXISTS professionals (
    details_id BIGINT PRIMARY KEY,
    user_id UUID,
    professional_type TEXT,
    instagram_url VARCHAR(255),
    skills BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (skills) REFERENCES filters(filter_id)
);