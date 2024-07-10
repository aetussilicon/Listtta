DROP VIEW professional_details;

CREATE VIEW
    professional_details AS
SELECT
    u.user_id,
    u.puid,
    u.full_name,
    u.user_gender,
    u.created_date,
    u.email,
    u.phone_number,
    u.whatsapp_contact,
    p.professional_type,
    p.instagram_url,
    ARRAY_AGG (ps.filter_id) AS skills,
    u.role,
    a.state,
    a.city,
    a.city_zone,
    a.district,
    a.street,
    a.complement,
    a.zip_code,
    u.profile_picture
FROM
    users u
    INNER JOIN users_address a ON u.user_id = a.user_id
    INNER JOIN professionals p ON u.user_id = p.user_id
    LEFT JOIN professionals_skills ps ON p.puid = ps.puid
GROUP BY
    u.user_id,
    u.puid,
    u.full_name,
    u.user_gender,
    u.created_date,
    u.email,
    u.phone_number,
    u.whatsapp_contact,
    p.professional_type,
    p.instagram_url,
    u.role,
    a.state,
    a.city,
    a.city_zone,
    a.district,
    a.street,
    a.complement,
    a.zip_code,
    u.profile_picture;