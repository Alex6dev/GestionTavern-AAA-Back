INSERT INTO player (id, email, nickname, password, enabled)
VALUES (1, 'Test@test.com', 'test1', 'azerty', 1);

INSERT INTO manager (id, name, reputation, chest, level, experience, player_id)
VALUES 
(1,'manager1', 10, 100, 1, 100, 1),
(2,'manager2', 10, 100, 1, 100, 1),
(3,'manager3', 10, 100, 1, 100, 1);

INSERT INTO customer (id, purse_of_gold, happiness, hunger, thirst, nausea_level, toilet, nausea_tolerance, 
alcohol_tolerance, gender, exp_given, alcohol_level )
VALUES
(1, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(2, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(3, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(4, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(5, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(6, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(7, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(8, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12);

INSERT INTO manager_customer (customer_id, manager_id)
VALUES
(1,2),
(2,2),
(3,2),
(4,2),
(5,2),
(6,1),
(7,1),
(8,1);


