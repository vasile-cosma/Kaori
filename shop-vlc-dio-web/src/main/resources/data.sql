

insert into category (description, image, name) values
    ('Fresco y vegetal, se elabora con hojas mínimamente oxidadas para preservar su color verde y sus matices herbales. es conocido por su suavidad y su perfil ligero.', 'green-tea.png', 'Té verde');

insert into category (description, image, name) values
    ('Raro y delicado, se somete a una ligera fermentación controlada que suaviza su sabor. ofrece notas dulces y un aroma cálido y aterciopelado.', 'yellow-tea.png', 'Té amarillo');

insert into category (description, image, name) values
    ('El menos procesado de todos. sus brotes tiernos dan una infusión sutil, dulce y floral, con una textura muy suave y refinada.', 'white-tea.png', 'Té blanco');

insert into category (description, image, name) values
    ('Semioxidado, combina características del té verde y negro. presenta sabores complejos que van de florales a tostados, con un aroma rico y elegante.', 'oolong-tea.png', 'Té oolong');

insert into category (description, image, name) values
    ('Té fermentado tradicional de china con cuerpo terroso y profundo. su maduración aporta un sabor cálido, robusto y ligeramente dulce.', 'red-tea.png', 'Té rojo');

insert into category (description, image, name) values
    ('Totalmente oxidado, ofrece una taza intensa con notas malteadas, especiadas o frutales. es el té más robusto y de sabor más definido.', 'black-tea.png', 'Té negro');


INSERT INTO brand (name, image) VALUES
                             ('Twinings', 'brand-01.png'),
                             ('Tetley', 'brand-02.png'),
                             ('Tazo', 'brand-03.png'),
                             ('Harney & Sons', 'brand-04.png'),
                             ('Dilmah', 'brand-05.png'),
                             ('Bigelow', 'brand-06.png'),
                             ('Yogi Tea', 'brand-07.png'),
                             ('Pukka Herbs', 'brand-08.png'),
                             ('Mariage Frères', 'brand-09.png'),
                             ('Kusmi Tea', 'brand-10.png'),
                             ('Fortnum & Mason', 'brand-11.png'),
                             ('The Republic of Tea', 'brand-12.png'),
                             ('Teavana', 'brand-13.png'),
                             ('Hornimans', 'brand-14.png'),
                             ('Pompadour', 'brand-15.png');

INSERT INTO product
(brand_id, code, desc, discount, img, name, price)
VALUES
    (1,'4006381333931',
     'Té verde japonés Sencha de alta calidad, cultivado en pequeñas plantaciones donde se controla cada fase del crecimiento. Su sabor es fresco, vegetal y ligeramente dulce, ideal para el consumo diario por sus propiedades antioxidantes y su bajo contenido en cafeína.',
     10,'green-tea-1.png','Sencha Premium',6.50),

    (2,'9780201379624',
     'Té negro procedente de la región de Assam en la India. De cuerpo intenso y sabor robusto, es perfecto para el desayuno. Su infusión oscura y aromática combina muy bien con leche o bebidas vegetales.',
     5,NULL,'Té Negro Assam Clásico',5.20),

    (3,'8414533043847',
     'Té blanco chino elaborado a partir de brotes jóvenes y hojas mínimamente procesadas. Su sabor es suave, delicado y ligeramente floral. Ideal para quienes buscan una infusión refinada y con bajo contenido en teína.',
     0,'white-tea-3.png','Pai Mu Tan',8.90),

    (5,'7501031311309',
     'Té rojo fermentado Pu Erh conocido por sus propiedades digestivas y depurativas. Su sabor terroso y profundo lo convierte en una bebida apreciada tras las comidas copiosas.',
     15,'red-tea-2.png','Pu Erh Digestivo',7.30),

    (3,'5013665103995',
     'Matcha japonés de grado ceremonial, molido a piedra y cultivado a la sombra para potenciar su clorofila. Presenta un color verde intenso, textura sedosa y un sabor umami equilibrado, ideal para consumir solo o en recetas.',
     20,'green-tea-3.png','Matcha Ceremonial',18.50),

    (4,'7622210449283',
     'Té Oolong tradicional de oxidación media que combina notas florales y tostadas. Su proceso artesanal le confiere una complejidad aromática única y una infusión dorada de gran elegancia.',
     5,'oolong-tea-1.png','Semioxidado',9.40),

    (5,'4009900545678',
     'Té verde aromatizado naturalmente con flores de jazmín. Su fragancia floral y sabor suave lo convierten en una opción relajante, ideal para consumir caliente o frío.',
     0,'green-tea-5.png','con Jazmín',6.10),

    (6,'8437008340125',
     'Té negro aromatizado con aceite natural de bergamota. Una mezcla clásica de sabor cítrico y elegante, perfecta para los amantes del té tradicional inglés.',
     10,'black-tea-1.png','Té Negro Earl Grey',4.80),

    (7,'6294001234567',
     'Infusión sudafricana de rooibos sin cafeína con un suave aroma a vainilla. Ideal para cualquier momento del día, especialmente por la noche.',
     0,'oolong-tea-3.png','Té Rooibos Vainilla',5.60),

    (9,'7613035975482',
     'Mezcla de té verde con hierbas naturales orientadas a la depuración del organismo. Aporta una sensación ligera y refrescante tras su consumo.',
     5,NULL,'Detox',5.90),

    (8,'5901234123457',
     'Té blanco aromatizado con melocotón natural que ofrece una infusión delicada y afrutada. Perfecto para quienes buscan sabores suaves y refrescantes.',
     0,'white-tea-2.png','Melocotón',7.80),

    (10,'4890008100309',
     'Té negro especiado con canela, cardamomo, clavo y jengibre. Inspirado en la receta tradicional india, es ideal para preparar con leche y disfrutar caliente.',
     12,'black-tea-4.png','Té Negro Chai Masala',6.70),

    (8,'6901234567890',
     'Té verde japonés mezclado con arroz tostado que aporta un aroma cálido y un sabor ligeramente tostado. Muy popular por su carácter reconfortante.',
     0,NULL,'Genmaicha',6.30),

    (9,'8001234567897',
     'Té negro de altura cultivado en las laderas del Himalaya. Conocido como el champán de los tés por su sabor fino, floral y ligeramente afrutado.',
     8,NULL,'Té Negro Darjeeling',9.90),

    (10,'8710908765432',
     'Té verde aromatizado con limón y jengibre, diseñado para aportar frescura y vitalidad. Ideal para consumir caliente o frío.',
     0,'green-tea-7.png','Limón y Jengibre',5.40),

    (11,'1234567890128',
     'Té Oolong semifermentado de sabor equilibrado, con notas florales y tostadas que combinan suavidad y carácter, ideal para disfrutar en cualquier momento del día.',
     10,'oolong-tea-4.png','Clásico',8.50),

    (11,'5601234567895',
     'Té japonés elaborado principalmente con tallos y ramas del té verde. Presenta un sabor suave y bajo contenido en cafeína, ideal para el consumo diario.',
     0,NULL,'Kukicha',6.20),

    (11,'3456789012345',
     'Té blanco enrollado en pequeñas perlas que se despliegan al infusionarse, liberando un aroma floral intenso y un sabor delicado.',
     15,'white-tea-4.png','Jazmín Perla',11.50),

    (12,'9780306406157',
     'Té negro de Sri Lanka con sabor equilibrado y aroma cítrico. Muy versátil y adecuado tanto para consumir solo como con leche.',
     0,'black-tea-6.png','Ceylon',5.10),

    (13,'4062139001234',
     'Té verde combinado con hojas de menta fresca, inspirado en la tradición marroquí. Refrescante y aromático, perfecto para compartir.',
     5,'green-tea-4.png','Menta',4.90),

    (8,'7891234567892',
     'Té Oolong con notas naturalmente cremosas que recuerdan a la leche. Muy apreciado por su suavidad y textura sedosa en boca.',
     0,'oolong-tea-1.png','Leche',8.40),

    (NULL,'2345678901236',
     'Té negro aromatizado con cacao que ofrece una experiencia intensa y reconfortante. Ideal para los amantes de sabores profundos.',
     10,'black-tea-5.png','Chocolate',6.80),

    (13,'8901234567896',
     'Té verde mezclado con frutos rojos deshidratados que aportan un sabor afrutado y ligeramente ácido. Muy agradable tanto caliente como frío.',
     0,NULL,'Frutas del Bosque',5.70),

    (15,'3210987654321',
     'Infusión delicada de té blanco combinada con flores de lavanda, pensada para momentos de relajación y bienestar.',
     0,'white-tea-1.png','Lavanda',8.10),

    (2,'6543210987654',
     'Té negro aromatizado con caramelo que ofrece una infusión dulce y reconfortante, perfecta para disfrutar en cualquier momento del día.',
     5,NULL,'Caramelo',5.60);


 INSERT INTO product_category (product_id, category_id)
 VALUES
    (1, 1),(5, 1),(10, 1),(13, 1),(15, 1),(20, 1),(23, 1),(7, 1), (17, 1),
    (3,3),(11,3),(18,3),(24,3),
    (21, 4), (6, 4),(16, 4),(10, 4),
    (4, 5),
    (2, 6), (8, 6), (25, 6), (12, 6), (14, 6), (19, 6), (22, 6), (20, 6);

