TRUNCATE users CASCADE;
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (1, 'root@localhost', 'I''m', 'GRoot', 'q1w2e3', 'root');
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (2, 'user@localhost', 'I''m', 'User', 'q1w2e3', 'user');