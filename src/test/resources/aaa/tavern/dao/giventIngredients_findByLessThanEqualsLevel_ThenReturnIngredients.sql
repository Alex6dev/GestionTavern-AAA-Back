INSERT INTO category (id, name)
VALUES (1, 'CategorieTest1');

INSERT INTO subcategory (id, name, category_id)
VALUES (1, 'SunCategoryTest1' , 1);

INSERT INTO ingredient (id, name, level, buying_price, subcategory_id)
VALUES (1, 'ingredientTest1' , 1 , 30, 1),
        (2, 'ingredientTest2' , 2 , 30, 1),
        (3, 'ingredientTest3' , 3 , 30, 1),
        (4, 'ingredientTest4' , 4 , 30, 1),
        (5, 'ingredientTest5' , 5 , 30, 1),
        (6, 'ingredientTest6' , 6 , 30, 1),
        (7, 'ingredientTest7' , 7 , 30, 1);