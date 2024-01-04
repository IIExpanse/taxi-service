SET session_replication_role = 'replica';

TRUNCATE client cascade;
ALTER SEQUENCE client_id_seq RESTART WITH 1;

SET session_replication_role = 'origin';

INSERT INTO client (first_name, last_name, email, age, login, created_at)
VALUES ('John', 'Doe', 'qwe@qwe.ru', 20, 'qwe', '2021-03-01T12:15:30Z'),
       ('Jane', 'Doe', 'zxc@zxc.by', 21, 'zxc', '2021-03-01T12:15:30Z'),
       ('Jack', 'Doe', 'rrr@rrr.com', 22, 'rrr', '2021-03-01T12:15:30Z');
