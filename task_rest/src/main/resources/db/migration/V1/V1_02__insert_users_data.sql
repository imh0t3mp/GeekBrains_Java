TRUNCATE users CASCADE;
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (1, 'root@localhost', 'I''m', 'GRoot', '$2y$12$jUWFwUvyHi9hoXcEWFAeh.2afXE0QDRa2CwtLKSBkfRSj2nIZ1sMS', 'root');
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (2, 'user@localhost', 'I''m', 'User', '$2y$12$yk8QEf4qASVDFwyUSZujzey3vLiMj9xuJJihVsZ6rXJxt31pXOOmm', 'user');