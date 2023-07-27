CREATE TABLE heroes (
                        id SERIAL PRIMARY KEY,
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