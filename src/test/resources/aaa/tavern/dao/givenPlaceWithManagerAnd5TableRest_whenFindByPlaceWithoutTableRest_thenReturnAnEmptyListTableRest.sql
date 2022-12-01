INSERT INTO player (id, email, nickname, password, enabled)
VALUES (1, 'Test1@example.com', 'TestPlayer1', 'azerty', 1);

INSERT INTO manager (id, name, reputation, chest, level, experience, player_id)
VALUES (1, 'ManagerTest1', 10, 10, 1, 10, 1);

INSERT INTO place (id, name, type, level, manager_id)
VALUES 
(1,'place1', 1, 1, 1),
(2,'place1', 1, 1, 1);

INSERT INTO table_rest (id, number_place, hygiene, pos_x, pos_y, place_id)
VALUES
(1, 2, 3, 4, 5, 1),
(2, 2, 3, 4, 5, 1),
(3, 2, 3, 4, 5, 1),
(4, 2, 3, 4, 5, 1),
(5, 2, 3, 4, 5, 1);
