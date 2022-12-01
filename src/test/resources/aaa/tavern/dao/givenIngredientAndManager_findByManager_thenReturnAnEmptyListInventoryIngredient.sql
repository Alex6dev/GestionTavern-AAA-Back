INSERT INTO category (id,name)
VALUES (1,'CategoryTest1');

INSERT INTO subcategory (id, name, category_id)
VALUES (1,'subcategoryTest1',1);

INSERT INTO ingredient (id, name, level, buying_price, subcategory_id)
VALUES 
        (1,'IngredientTest1', 1,20,1),
        (2,'IngredientTest2', 1,20,1),
        (3,'IngredientTest3', 1,20,1),
        (4,'IngredientTest4', 1,20,1),
        (5,'IngredientTest5', 1,20,1),
        (6,'IngredientTest6', 1,20,1);

INSERT INTO player (id, email, nickname, password, enabled)
VALUES (1, 'Test1@example.com', 'TestPlayer1', 'azerty', 1);

INSERT INTO manager (id, name, reputation, chest, level, experience, player_id)
VALUES  
        (1, 'ManagerTest1', 10, 10, 1, 10, 1),
        (2, 'ManagerTest2', 10, 10, 1, 10, 1),
        (3, 'ManagerTest3', 10, 10, 1, 10, 1);

INSERT INTO inventory_ingredient (manager_id, ingredient_id, quantity)
VALUES  
        (1, 1, 4),
        (1, 2, 4),
        (1, 3, 4),
        (2, 4, 4),
        (2, 5, 4),
        (2, 6, 4);
