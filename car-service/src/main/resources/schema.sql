CREATE TABLE IF NOT EXISTS car
(
    id              INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    brand           VARCHAR(255) NOT NULL,
    model           VARCHAR(255) NOT NULL,
    production_date VARCHAR(255) NOT NULL,
    mileage         INTEGER,
    created_at      TIMESTAMP
);