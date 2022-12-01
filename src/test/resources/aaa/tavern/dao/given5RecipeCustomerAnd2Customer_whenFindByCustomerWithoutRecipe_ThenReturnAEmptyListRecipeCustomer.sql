INSERT INTO customer (id, purse_of_gold, happiness, hunger, thirst, nausea_level, toilet, nausea_tolerance, 
alcohol_tolerance, gender, exp_given, alcohol_level )
VALUES
(1, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(2, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12);

INSERT INTO category (id, name)
VALUES
(1, 'testCategory');

INSERT INTO subcategory (id, name, category_id)
VALUES
(1,'testSubCategory',1);

INSERT INTO recipe (id, name, selling_price, level, consommation_time, preparation_time, peremption_date, exp_given,
subcategory_id)
VALUES
(1, 'recipe1', 10, 1, 5, 5, '2022-01-02',1,1),
(2, 'recipe2', 10, 1, 5, 5, '2022-01-02',1,1),
(3, 'recipe3', 10, 1, 5, 5, '2022-01-02',1,1),
(4, 'recipe4', 10, 1, 5, 5, '2022-01-02',1,1),
(5, 'recipe5', 10, 1, 5, 5, '2022-01-02',1,1);

INSERT INTO recipe_customer (customer_id, recipe_id)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5);