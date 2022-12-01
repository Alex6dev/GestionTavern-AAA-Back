INSERT INTO category (id,name)
VALUES
(1,'testCategory');

INSERT INTO subcategory (id,name, category_id)
VALUES
(1,'testSubCategory',1);

INSERT INTO recipe (id,name, selling_price, level, consommation_time, preparation_time, peremption_date, exp_given, subcategory_id)
VALUES
(1,'recipe1', 10, 3, 1, 1, '2022-01-02',1,1),
(2,'recipe2', 10, 5, 1, 1, '2022-01-02',1,1),
(3,'recipe3', 10, 7, 1, 1, '2022-01-02',1,1),
(4,'recipe3', 10, 3, 1, 1, '2022-01-02',1,1);