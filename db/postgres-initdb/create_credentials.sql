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

-- Create 'heroes' table
CREATE TABLE heroes (
                        hero_id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        name VARCHAR(45) NOT NULL,
                        class VARCHAR(45) NOT NULL,
                        level INT NOT NULL,
                        hit_points INT NOT NULL,
                        attack INT NOT NULL,
                        damage INT NOT NULL,
                        ac INT NOT NULL,
                        CONSTRAINT user_hero_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- Insert roles as part of the initial setup
INSERT INTO roles (name) VALUES ('HEROES_READ');
INSERT INTO roles (name) VALUES ('HEROES_WRITE');
INSERT INTO roles (name) VALUES ('HEROES_EDIT');
INSERT INTO roles (name) VALUES ('HEROES_DELETE');
