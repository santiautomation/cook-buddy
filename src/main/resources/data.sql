-- Categorias Principales
INSERT INTO CATEGORY(name) VALUES ('Entradas'),('Platos Fuertes'), ('Postres'),('Bebidas');


-- Ingredientes
insert into ingredient(name) values ('Papa'),('Cebolla'),('Tomate'),('Lomo de Cerdo'),('Col'),('Remolacha'),('Vainilla');

-- Detalles
-- TODO

-- Subcategorias
insert into subcategory(category_id, name) values
(1, 'No se'), (2, 'Que poner'), (3, 'Porque no se'), (4, 'Cocinar');

-- Recetas
insert into recipe(name, description, subcategory_id, status, details) values
('Chocotorta', 'Mmmmm chocolateee', 3, true, 'Link a youtube'),
('Poshito', 'Poio al horno solo', 2, true, 'Link a youtube'),
('Gin Tonic', 'Fondo blanco', 4, true, 'Link a youtube');

-- RecetaXingrediente
insert into recipe_ingredients(recipe_id, ingredients_id, status) values
(1,1, true), (1,2, true), (1,3, true), (2,5, true), (2,1, true), (2,6, true),
(3,4, true), (3,3, true), (3,2, true), (3,7, true);