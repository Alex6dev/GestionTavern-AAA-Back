INSERT INTO player (id, email, nickname, password, enabled)
VALUES
(1, 'TestPlayer@test.com', 'Test', 'azerty', 1);

INSERT INTO category (id, name)
VALUES
(1, 'testCategory');

INSERT INTO subcategory (id, name, category_id)
VALUES
(1,'testSubCategory',1);

INSERT INTO manager (id, name, reputation, chest, level, experience, player_id)
VALUES
(1, 'Manager', 10, 100, 5, 50, 1);

INSERT INTO recipe (id, name, selling_price, level, consommation_time, preparation_time, peremption_date, exp_given, subcategory_id)
VALUES
(1, 'recipe1', 10, 1, 5, 5, '2022-01-02',1,1),
(2, 'recipe2', 10, 2, 5, 5, '2022-01-02',1,1),
(3, 'recipe3', 10, 3, 5, 5, '2022-01-02',1,1),
(4, 'recipe4', 10, 4, 5, 5, '2022-01-02',1,1),
(5, 'recipe5', 10, 5, 5, 5, '2022-01-02',1,1),
(6, 'recipe6', 10, 6, 5, 5, '2022-01-02',1,1),
(7, 'recipe7', 10, 7, 5, 5, '2022-01-02',1,1),
(8, 'recipe8', 10, 8, 5, 5, '2022-01-02',1,1);