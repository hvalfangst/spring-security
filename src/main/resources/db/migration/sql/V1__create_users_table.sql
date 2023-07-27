CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       email VARCHAR(45) NOT NULL,
                       full_name VARCHAR(45) NOT NULL,
                       password VARCHAR(64) NOT NULL,
                       enabled BOOLEAN DEFAULT NULL,
                       UNIQUE (email)
);