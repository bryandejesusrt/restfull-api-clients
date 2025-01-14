CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    second_last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    address TEXT NOT NULL,
    phone VARCHAR(20) NOT NULL,
    country_code VARCHAR(5) NOT NULL,
    demonym VARCHAR(100) NOT NULL
);