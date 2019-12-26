TRUNCATE users CASCADE;
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (1, 'root@localhost', 'I''m', 'GRoot', '99adc231b045331e514a516b4b7680f588e3823213abe91738bc3ad67b2f6fcb3c64efb93d1802588d3ccc1a49efbae1ce20cb43df36b38651f11fa75678e8', 'root');
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (2, 'user@localhost', 'I''m', 'User', 'b14361404c78ffd549c3db443c3fede2f3e534d73f78f7731ed97d4a436a9fd9db05ee8b325c0ad36438b43fec851c204fc1c1edb21d941c0e9e2c1ce2', 'user');