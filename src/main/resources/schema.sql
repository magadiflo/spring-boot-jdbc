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

-- Para la relación One-to-One: owners y addresses
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS owners;

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
