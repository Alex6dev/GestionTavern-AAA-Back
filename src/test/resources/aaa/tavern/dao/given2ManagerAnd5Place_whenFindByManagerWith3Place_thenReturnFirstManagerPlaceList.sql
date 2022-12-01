INSERT INTO player (id, email, nickname, password, enabled)
VALUES (1, 'Test@test.com', 'test1', 'azerty', 1);

INSERT INTO manager (id, name, reputation, chest, level, experience, player_id)
VALUES 
(1,'manager1', 10, 100, 1, 100, 1),
(2,'manager2', 10, 100, 1, 100, 1);

INSERT INTO place (id, name, type, level, manager_id)
VALUES
(1,'place1', 1, 1, 1),
(2,'place2', 1, 1, 1),
(3,'place3', 1, 1, 1),
(4,'place4', 1, 1, 2),
(5,'place5', 1, 1, 2);