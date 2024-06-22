DROP VIEW IF EXISTS professional_details;

CREATE VIEW professional_details AS
SELECT u.user_id,
       u.user_tag,
       u.puid,
       u.full_name,
       u.user_gender,
       u.created_date,
       u.email,
       u.password,
       u.tax_number,
       u.phone_number,
       u.whatsapp_contact,
       u.role,
       a.state,
       a.city,
       p.professional_type,
       p.instagram_url,
       ARRAY_AGG(ps.filter_id) AS skills,
       u.profile_picture
FROM users u
INNER JOIN users_address a ON u.user_id = a.user_id
INNER JOIN professionals p ON u.user_id = p.user_id
LEFT JOIN professionals_skills ps ON p.puid = ps.puid
GROUP BY
    u.user_id,
    u.user_tag,
    u.puid,
    u.full_name,
    u.user_gender,
    u.created_date,
    u.email,
    u.password,
    u.tax_number,
    u.phone_number,
    u.whatsapp_contact,
    u.role,
    a.state,
    a.city,
    p.professional_type,
    p.instagram_url,
    u.profile_picture;