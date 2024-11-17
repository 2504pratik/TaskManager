CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
INSERT INTO users (username, password)
VALUES ('admin', '$2a$12$Y2ztT.zp04biHMZ7kww2iO.DzuUEw0eMG3Z1BteQPJrEU5KtJSbHG');
-- This creates a user with username: admin and password: password