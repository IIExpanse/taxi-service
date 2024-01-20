CREATE TABLE car
(
    id              SERIAL PRIMARY KEY,
    brand           VARCHAR(255) NOT NULL,
    model           VARCHAR(255) NOT NULL,
    production_date VARCHAR(255) NOT NULL,
    mileage         INTEGER,
    created_at      TIMESTAMP
);