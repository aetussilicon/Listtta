CREATE TABLE IF NOT EXISTS professional_skills (
    details_id BIGINT REFERENCES professionals(details_id),
    filter_id BIGINT REFERENCES filters(filter_id),
    PRIMARY KEY (details_id, filter_id)
);
