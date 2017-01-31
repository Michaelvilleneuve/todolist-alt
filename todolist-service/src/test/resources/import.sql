-- TASKS STATUS
INSERT INTO tasks_status(name) VALUES ('STARTED');
INSERT INTO tasks_status(name) VALUES ('FINISHED');
INSERT INTO tasks_status(name) VALUES ('ARCHIVED');
INSERT INTO tasks_status(name) VALUES ('DELEGATION_PENDING');
INSERT INTO tasks_status(name) VALUES ('DELEGATED');

-- TASKS
INSERT INTO tasks(name, id_task_status, closedDate, beginDate) VALUES ('Hikingyo', 1, NULL, DATE '2017-1-27');
INSERT INTO tasks(name, id_task_status, closedDate, beginDate) VALUES ('StartedTask', 1, NULL, DATE '2016-8-10');
INSERT INTO tasks(name, id_task_status, closedDate) VALUES ('FinishedTask', 2, DATE '2016-8-10');

-- USERS
INSERT INTO users(name) VALUES('userTest');
INSERT INTO users(name) VALUES('delegateUser');

-- TASKS OWNERS
INSERT INTO tasks_owners(id_task, id_user) VALUES(1, 1);
INSERT INTO tasks_owners(id_task, id_user) VALUES(2, 1);
INSERT INTO tasks_owners(id_task, id_user) VALUES(2, 2);