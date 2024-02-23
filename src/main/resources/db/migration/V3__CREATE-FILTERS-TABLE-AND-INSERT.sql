CREATE TABLE IF NOT EXISTS filters (
    filter_id SERIAL PRIMARY KEY NOT NULL,
    filter_name VARCHAR(30) NOT NULL,
    display_name VARCHAR(50)
);

INSERT INTO filters (filter_name, display_name) VALUES
    ('abstract', 'Abstrato'),
    ('watercolor', 'Aquarela'),
    ('fusion', 'Art Fusion'),
    ('black-gray', 'Black & Gray (preto e cinza)'),
    ('blackwork', 'Blackwork'),
    ('bold-line', 'Bold-Line'),
    ('scar-coverage', 'Cobertura de Cicatriz'),
    ('tattoo-coverage', 'Cobertura de Tattoo'),
    ('colorful', 'Colorida'),
    ('stylized', 'Estilizada'),
    ('fineline', 'Fineline (traços finos)'),
    ('floral', 'Floral'),
    ('freehand', 'Freehand'),
    ('from-hell', 'From Hell'),
    ('gek', 'Geek'),
    ('geometric', 'Geométrico'),
    ('glitter', 'Glitter'),
    ('handpoke', 'Handpoke'),
    ('kwaii', 'kwaii'),
    ('lettering', 'Lettering'),
    ('mandala', 'Mandala'),
    ('maori', 'Maori'),
    ('neo-tradicional', 'Neo Tradicional'),
    ('new-school', 'New School'),
    ('old-school', 'Old School'),
    ('oriental', 'Oriental'),
    ('patch', 'Patch (bordado)'),
    ('pet', 'Pet'),
    ('pixel', 'Pixel'),
    ('pointillism', 'Pontilhismo'),
    ('realism', 'Realismo'),
    ('sketch', 'Sketch'),
    ('sticker', 'Sticker (Adesivo)'),
    ('tribal', 'Tribal');