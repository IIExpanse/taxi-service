CREATE TABLE IF NOT EXISTS client
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    age        INTEGER,
    login      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);