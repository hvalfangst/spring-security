CREATE TABLE users_roles (
                             user_id INT NOT NULL,
                             role_id INT NOT NULL,
                             CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
                             CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (role_id),
                             PRIMARY KEY (user_id, role_id)
);