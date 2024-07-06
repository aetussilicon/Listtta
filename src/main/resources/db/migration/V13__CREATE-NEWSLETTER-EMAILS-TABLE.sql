CREATE SEQUENCE newsletter_emails_seq;

CREATE TABLE
    newsletter_emails (
        letter_id BIGINT NOT NULL UNIQUE,
        email VARCHAR(100) NOT NULL UNIQUE,
        created_date DATE NOT NULL
    );