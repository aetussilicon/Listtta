CREATE VIEW professional_details AS
SELECT u.user_id, u.user_tag, u.full_name, u.user_gender, u.created_date, u.email,
       u.password, u.tax_number, u.phone_number, u.whatsapp_contact, u.role,
       p.professional_type, p.instagram_url, p.skills
FROM users u
INNER JOIN professionals p ON u.user_id = p.user_id;