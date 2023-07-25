-- Create 'users' table
CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       email VARCHAR(45) NOT NULL,
                       full_name VARCHAR(45) NOT NULL,
                       password VARCHAR(64) NOT NULL,
                       enabled BOOLEAN DEFAULT NULL,
                       UNIQUE (email)
);

-- Create 'roles' table
CREATE TABLE roles (
                       role_id SERIAL PRIMARY KEY,
                       name VARCHAR(45) NOT NULL
);

-- Create 'users_roles' table
CREATE TABLE users_roles (
                             user_id INT NOT NULL,
                             role_id INT NOT NULL,
                             CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
                             CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (role_id),
                             PRIMARY KEY (user_id, role_id)
);

-- Insert roles as part of the initial setup
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('CREATOR');
INSERT INTO roles (name) VALUES ('EDITOR');
INSERT INTO roles (name) VALUES ('ADMIN');