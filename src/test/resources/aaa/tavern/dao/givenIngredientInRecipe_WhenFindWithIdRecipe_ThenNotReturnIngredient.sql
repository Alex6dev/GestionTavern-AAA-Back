INSERT INTO category (id, name)
VALUES
(1, 'testCategory');

INSERT INTO subcategory (id, name, category_id)
VALUES
(1,'testSubCategory',1);

INSERT INTO recipe (id, name, selling_price, level, consommation_time, preparation_time, peremption_date, exp_given, subcategory_id)
VALUES
(1, 'recipe1', 10, 1, 1, 1, '2022-01-02',1,1),
(2, 'recipe2', 10, 1, 1, 1, '2022-01-02',1,1),
(3, 'recipe3', 10, 1, 1, 1, '2022-01-02',1,1);

INSERT INTO ingredient(id, name, level, buying_price,subcategory_id)
VALUES
(1, 'testIngredient1',1,2,1),
(2, 'testIngredient2',1,2,1),
(3, 'testIngredient3',1,2,1),
(4, 'testIngredient4',1,2,1),
(5, 'testIngredient5',1,2,1);

INSERT INTO recipe_ingredient (ingredient_id,recipe_id,quantity)
VALUES
(1,1,1),
(2,1,2),
(3,1,10),
(1,2,1);