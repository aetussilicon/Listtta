CREATE SEQUENCE professionals_skills_seq;

CREATE TABLE IF NOT EXISTS professionals_skills (
    skill_id BIGINT PRIMARY KEY,
    puid VARCHAR(20), 
    filter_id BIGINT,
    FOREIGN KEY (puid) REFERENCES professionals(puid),
    FOREIGN KEY (filter_id) REFERENCES filters(filter_id)
);
