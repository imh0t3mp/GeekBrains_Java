TRUNCATE task_repo CASCADE ;
INSERT INTO task_repo (id, date_add, description, task_name, task_status, owner_id, performer_id)
VALUES (1, '2019-12-15 23:01:31.000000', 'Первая', 'T1', 'CREATED', 1, 2);
INSERT INTO task_repo (id, date_add, description, task_name, task_status, owner_id, performer_id)
VALUES (2, '2019-12-15 23:01:58.000000', 'Вторая', 'T2', 'CREATED', 2, 1);