SET session_replication_role = 'replica';

TRUNCATE car cascade;
ALTER SEQUENCE car_id_seq RESTART WITH 1;

SET session_replication_role = 'origin';

INSERT INTO car (brand, model, production_date, mileage, created_at)
VALUES ('Skoda', 'Rapid', '12.2018', 100000, '2021-03-01T12:15:30Z'),
       ('Audi', '100', '01.1999', 500000, '2021-03-01T12:15:30Z'),
       ('Opel', 'Omega', '08.2010', 250000, '2021-03-01T12:15:30Z');