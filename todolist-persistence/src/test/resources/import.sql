-- TASKS STATUS
INSERT INTO tasks_status(name) VALUES ('STARTED');
INSERT INTO tasks_status(name) VALUES ('FINISHED');

-- TASKS
INSERT INTO tasks(name, id_task_status, closedDate) VALUES ('Hikingyo', 1, NULL);
INSERT INTO tasks(name, id_task_status, closedDate) VALUES ('StartedTask', 1, NULL);
INSERT INTO tasks(name, id_task_status, closedDate) VALUES ('FinishedTask', 2, DATE '2016-8-10');