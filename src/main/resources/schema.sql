DROP TABLE IF EXISTS users_authorities;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(256) NOT NULL,
    account_non_expired BIT,
    account_non_locked BIT,
    credentials_non_expired BIT,
    enabled BIT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email_address VARCHAR(100),
    birthdate DATE
);

CREATE TABLE authorities(
    id INT AUTO_INCREMENT PRIMARY KEY,
    authority VARCHAR(50)
);

CREATE TABLE users_authorities(
    user_id INT NOT NULL,
    authority_id INT NOT NULL,
    CONSTRAINT pk_users_authorities PRIMARY KEY (user_id, authority_id),
    CONSTRAINT fk_users__users_authorities FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_authorities__users_authorities FOREIGN KEY (authority_id) REFERENCES authorities(id)
);

-- Orden de eliminación de tablas
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS owners;

-- Para la relación One-to-One: owners y addresses
CREATE TABLE owners(
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL
);

CREATE TABLE addresses(
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT NOT NULL,
    address_line TEXT NOT NULL,
    CONSTRAINT uq_owner_id UNIQUE(owner_id),
    CONSTRAINT fk_owners_addresses FOREIGN KEY(owner_id) REFERENCES owners(id)
);

-- Para la relación One-to-Many: tasks y comments
-- Relación One-to-Many: owners y tasks
CREATE TABLE tasks(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    published_on DATETIME NOT NULL,
    updated_on DATETIME,
    owner_id INT,
    CONSTRAINT fk_owners_tasks FOREIGN KEY (owner_id) REFERENCES owners(id)
);

CREATE TABLE comments(
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    published_on DATETIME NOT NULL,
    updated_on DATETIME,
    CONSTRAINT fk_tasks_comments FOREIGN KEY(task_id) REFERENCES tasks(id)
);

DROP TABLE IF EXISTS tutorials;

CREATE TABLE tutorials(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);