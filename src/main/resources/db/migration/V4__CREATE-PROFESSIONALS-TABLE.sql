CREATE SEQUENCE professionals_seq;

CREATE TABLE IF NOT EXISTS professionals (
    details_id BIGINT PRIMARY KEY,
    user_id UUID NOT NULL UNIQUE,
    puid VARCHAR(20) NOT NULL UNIQUE,
    user_tag VARCHAR(10) UNIQUE,
    professional_type TEXT NOT NULL,
    instagram_url VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (puid) REFERENCES users(puid)
);