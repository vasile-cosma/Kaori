

insert into category (description, image, name) values
    ('fresco y vegetal, se elabora con hojas mínimamente oxidadas para preservar su color verde y sus matices herbales. es conocido por su suavidad y su perfil ligero.', 'green-tea.png', 'té verde');

insert into category (description, image, name) values
    ('raro y delicado, se somete a una ligera fermentación controlada que suaviza su sabor. ofrece notas dulces y un aroma cálido y aterciopelado.', 'yellow-tea.png', 'té amarillo');

insert into category (description, image, name) values
    ('el menos procesado de todos. sus brotes tiernos dan una infusión sutil, dulce y floral, con una textura muy suave y refinada.', 'white-tea.png', 'té blanco');

insert into category (description, image, name) values
    ('semioxidado, combina características del té verde y negro. presenta sabores complejos que van de florales a tostados, con un aroma rico y elegante.', 'oolong-tea.png', 'té oolong');

insert into category (description, image, name) values
    ('té fermentado tradicional de china con cuerpo terroso y profundo. su maduración aporta un sabor cálido, robusto y ligeramente dulce.', 'red-tea.png', 'té rojo');

insert into category (description, image, name) values
    ('totalmente oxidado, ofrece una taza intensa con notas malteadas, especiadas o frutales. es el té más robusto y de sabor más definido.', 'black-tea.png', 'té negro');


INSERT INTO product
(product_brand, product_code, product_description, product_discount, product_image, product_name, product_price)
VALUES
    ('Yamato Tea','4006381333931',
     'Té verde japonés Sencha de alta calidad, cultivado en pequeñas plantaciones donde se controla cada fase del crecimiento. Su sabor es fresco, vegetal y ligeramente dulce, ideal para el consumo diario por sus propiedades antioxidantes y su bajo contenido en cafeína.',
     10,'https://example.com/img/te-sencha.jpg','Té Verde Sencha Premium',6.50),

    ('Royal Assam','9780201379624',
     'Té negro procedente de la región de Assam en la India. De cuerpo intenso y sabor robusto, es perfecto para el desayuno. Su infusión oscura y aromática combina muy bien con leche o bebidas vegetales.',
     5,NULL,'Té Negro Assam Clásico',5.20),

    (NULL,'8414533043847',
     'Té blanco chino elaborado a partir de brotes jóvenes y hojas mínimamente procesadas. Su sabor es suave, delicado y ligeramente floral. Ideal para quienes buscan una infusión refinada y con bajo contenido en teína.',
     0,'https://example.com/img/te-blanco.jpg','Té Blanco Pai Mu Tan',8.90),

    (NULL,'7501031311309',
     'Té rojo fermentado Pu Erh conocido por sus propiedades digestivas y depurativas. Su sabor terroso y profundo lo convierte en una bebida apreciada tras las comidas copiosas.',
     15,NULL,'Té Rojo Pu Erh Digestivo',7.30),

    ('Matcha House','5013665103995',
     'Matcha japonés de grado ceremonial, molido a piedra y cultivado a la sombra para potenciar su clorofila. Presenta un color verde intenso, textura sedosa y un sabor umami equilibrado, ideal para consumir solo o en recetas.',
     20,'https://example.com/img/matcha.jpg','Té Verde Matcha Ceremonial',18.50),

    ('Dragon Leaf','7622210449283',
     'Té Oolong tradicional de oxidación media que combina notas florales y tostadas. Su proceso artesanal le confiere una complejidad aromática única y una infusión dorada de gran elegancia.',
     5,NULL,'Té Oolong Semioxidado',9.40),

    ('Golden Lotus','4009900545678',
     'Té verde aromatizado naturalmente con flores de jazmín. Su fragancia floral y sabor suave lo convierten en una opción relajante, ideal para consumir caliente o frío.',
     0,'https://example.com/img/jazmin.jpg','Té Verde con Jazmín',6.10),

    (NULL,'8437008340125',
     'Té negro aromatizado con aceite natural de bergamota. Una mezcla clásica de sabor cítrico y elegante, perfecta para los amantes del té tradicional inglés.',
     10,NULL,'Té Negro Earl Grey',4.80),

    ('Cape Nature','6294001234567',
     'Infusión sudafricana de rooibos sin cafeína con un suave aroma a vainilla. Ideal para cualquier momento del día, especialmente por la noche.',
     0,'https://example.com/img/rooibos.jpg','Té Rooibos Vainilla',5.60),

    (NULL,'7613035975482',
     'Mezcla de té verde con hierbas naturales orientadas a la depuración del organismo. Aporta una sensación ligera y refrescante tras su consumo.',
     5,NULL,'Té Verde Detox',5.90),

    ('White Leaf','5901234123457',
     'Té blanco aromatizado con melocotón natural que ofrece una infusión delicada y afrutada. Perfecto para quienes buscan sabores suaves y refrescantes.',
     0,'https://example.com/img/blanco-melocoton.jpg','Té Blanco Melocotón',7.80),

    ('Spice Route','4890008100309',
     'Té negro especiado con canela, cardamomo, clavo y jengibre. Inspirado en la receta tradicional india, es ideal para preparar con leche y disfrutar caliente.',
     12,NULL,'Té Negro Chai Masala',6.70),

    (NULL,'6901234567890',
     'Té verde japonés mezclado con arroz tostado que aporta un aroma cálido y un sabor ligeramente tostado. Muy popular por su carácter reconfortante.',
     0,'https://example.com/img/genmaicha.jpg','Té Verde Genmaicha',6.30),

    ('Himalayan Tea','8001234567897',
     'Té negro de altura cultivado en las laderas del Himalaya. Conocido como el champán de los tés por su sabor fino, floral y ligeramente afrutado.',
     8,NULL,'Té Negro Darjeeling',9.90),

    (NULL,'8710908765432',
     'Té verde aromatizado con limón y jengibre, diseñado para aportar frescura y vitalidad. Ideal para consumir caliente o frío.',
     0,NULL,'Té Verde Limón y Jengibre',5.40),

    ('Orient Tea','1234567890128',
     'Variante de té rojo Pu Erh combinada con cáscara de naranja natural que suaviza su sabor intenso y aporta un aroma cítrico muy agradable.',
     10,'https://example.com/img/pu-erh-naranja.jpg','Té Rojo Pu Erh Naranja',7.90),

    ('Zen Garden','5601234567895',
     'Té japonés elaborado principalmente con tallos y ramas del té verde. Presenta un sabor suave y bajo contenido en cafeína, ideal para el consumo diario.',
     0,NULL,'Té Verde Kukicha',6.20),

    (NULL,'3456789012345',
     'Té blanco enrollado en pequeñas perlas que se despliegan al infusionarse, liberando un aroma floral intenso y un sabor delicado.',
     15,'https://example.com/img/perlas.jpg','Té Blanco Jazmín Perla',11.50),

    ('Ceylon Gold','9780306406157',
     'Té negro de Sri Lanka con sabor equilibrado y aroma cítrico. Muy versátil y adecuado tanto para consumir solo como con leche.',
     0,NULL,'Té Negro Ceylon',5.10),

    ('Moroccan Style','4062139001234',
     'Té verde combinado con hojas de menta fresca, inspirado en la tradición marroquí. Refrescante y aromático, perfecto para compartir.',
     5,'https://example.com/img/menta.jpg','Té Verde Menta',4.90),

    (NULL,'7891234567892',
     'Té Oolong con notas naturalmente cremosas que recuerdan a la leche. Muy apreciado por su suavidad y textura sedosa en boca.',
     0,NULL,'Té Oolong Leche',8.40),

    ('Sweet Leaf','2345678901236',
     'Té negro aromatizado con cacao que ofrece una experiencia intensa y reconfortante. Ideal para los amantes de sabores profundos.',
     10,'https://example.com/img/choco-te.jpg','Té Negro Chocolate',6.80),

    (NULL,'8901234567896',
     'Té verde mezclado con frutos rojos deshidratados que aportan un sabor afrutado y ligeramente ácido. Muy agradable tanto caliente como frío.',
     0,NULL,'Té Verde Frutas del Bosque',5.70),

    ('Calm Nature','3210987654321',
     'Infusión delicada de té blanco combinada con flores de lavanda, pensada para momentos de relajación y bienestar.',
     0,'https://example.com/img/lavanda.jpg','Té Blanco Lavanda',8.10),

    (NULL,'6543210987654',
     'Té negro aromatizado con caramelo que ofrece una infusión dulce y reconfortante, perfecta para disfrutar en cualquier momento del día.',
     5,NULL,'Té Negro Caramelo',5.60);

