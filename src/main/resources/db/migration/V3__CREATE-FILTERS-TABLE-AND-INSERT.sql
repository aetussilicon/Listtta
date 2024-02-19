CREATE TABLE IF NOT EXISTS filters (
    filter_id SERIAL PRIMARY KEY NOT NULL,
    filter_name VARCHAR(30) NOT NULL,
    display_name VARCHAR(50)
);