INSERT INTO users(id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name, email_address, birthdate) VALUES(2, 'admin', '123456', true, true, true, true, 'Martín', 'Díaz', 'martin@gmail.com', '2000-01-15');
INSERT INTO users(id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name, email_address, birthdate) VALUES(4, 'user', '123456', true, true, true, true, 'Clara', 'Díaz', 'clara@gmail.com', '1998-07-28');
INSERT INTO users(id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name, email_address, birthdate) VALUES(6, 'karen', '123456', true, true, true, true, 'Karen', 'Díaz', 'karen@gmail.com', '2000-06-03');
INSERT INTO authorities(id, authority) VALUES(3, 'ROLE_USER');
INSERT INTO authorities(id, authority) VALUES(5, 'ROLE_ADMIN');
INSERT INTO authorities(id, authority) VALUES(7, 'ROLE_DEVELOPER');
INSERT INTO users_authorities(user_id, authority_id) VALUES(2, 3);
INSERT INTO users_authorities(user_id, authority_id) VALUES(2, 5);
INSERT INTO users_authorities(user_id, authority_id) VALUES(2, 7);
INSERT INTO users_authorities(user_id, authority_id) VALUES(4, 3);
INSERT INTO users_authorities(user_id, authority_id) VALUES(4, 5);
INSERT INTO users_authorities(user_id, authority_id) VALUES(6, 7);

-- Para la relación One-to-One: owners y addresses
INSERT INTO owners(id, full_name, email, username) VALUES(10, 'Martín Díaz', 'martin@gmail.com', 'martin');
INSERT INTO owners(id, full_name, email, username) VALUES(20, 'Karen Caldas', 'karen@gmail.com', 'karen');
INSERT INTO addresses(id, owner_id, address_line) VALUES(1, 10, 'Av. José Olaya, Chimbote');
INSERT INTO addresses(id, owner_id, address_line) VALUES(2, 20, 'Pichari, La Convención, Cuzco');

-- Para la relación One-to-Many: tasks y comments
-- Relación One-to-Many: owners y tasks
INSERT INTO tasks(id, title, content, published_on, updated_on, owner_id) VALUES(10, 'Proyecto envío email', 'Este proyecto enviará emails', now(), now(), 20);
INSERT INTO tasks(id, title, content, published_on, updated_on, owner_id) VALUES(20, 'Renovación jardinería', 'Este proyecto renovará el jardín', now(), now(), 10);
INSERT INTO tasks(id, title, content, published_on, updated_on, owner_id) VALUES(30, 'Pintado fachada', 'Trabajamos para remodelar fachada', now(), now(), 10);
INSERT INTO tasks(id, title, content, published_on, updated_on, owner_id) VALUES(40, 'Compra mercado', 'Compras del mes', now(), now(), 10);
INSERT INTO comments(id, task_id, name, content, published_on, updated_on) VALUES(1, 10, 'Desarrollador Senior', 'Me uno al proyecto', now(), now());
INSERT INTO comments(id, task_id, name, content, published_on, updated_on) VALUES(2, 10, 'Desarrollador Junior', 'Quiero participar', now(), now());
INSERT INTO comments(id, task_id, name, content, published_on, updated_on) VALUES(3, 20, 'vecino', 'Excelente decisión', now(), now());
INSERT INTO comments(id, task_id, name, content, published_on, updated_on) VALUES(4, 30, 'Karen', 'Colores suaves sería genial', now(), now());
