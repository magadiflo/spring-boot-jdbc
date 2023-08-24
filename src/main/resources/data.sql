INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name, email_address, birthdate) VALUES('admin', '123456', true, true, true, true, 'Martín', 'Díaz', 'martin@gmail.com', '2000-01-15');
INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name, email_address, birthdate) VALUES('user', '123456', true, true, true, true, 'Clara', 'Díaz', 'clara@gmail.com', '1998-07-28');
INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name, email_address, birthdate) VALUES('karen', '123456', true, true, true, true, 'Karen', 'Díaz', 'karen@gmail.com', '2000-06-03');

-- Para la relación One-to-One: owners y addresses
INSERT INTO owners(id, full_name, email, username) VALUES(10, 'Martín Díaz', 'martin@gmail.com', 'martin');
INSERT INTO owners(id, full_name, email, username) VALUES(20, 'Karen Caldas', 'karen@gmail.com', 'karen');
INSERT INTO addresses(id, owner_id, address_line) VALUES(1, 10, 'Av. José Olaya, Chimbote');
INSERT INTO addresses(id, owner_id, address_line) VALUES(2, 20, 'Pichari, La Convención, Cuzco');