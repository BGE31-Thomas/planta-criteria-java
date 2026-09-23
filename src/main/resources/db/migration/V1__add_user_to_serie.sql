ALTER TABLE serie
ADD COLUMN user_id INTEGER;

ALTER TABLE serie
ADD CONSTRAINT fk_serie_user
    FOREIGN KEY (user_id)
    REFERENCES users(id);