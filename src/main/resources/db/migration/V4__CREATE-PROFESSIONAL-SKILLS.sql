CREATE TABLE IF NOT EXISTS professional_skills (
    user_id UUID,
    filter_id BIGINT,
    PRIMARY KEY (user_id, filter_id),
    FOREIGN KEY (user_id) REFERENCES professionals(user_id),
    FOREIGN KEY (filter_id) REFERENCES filters(filter_id)
);