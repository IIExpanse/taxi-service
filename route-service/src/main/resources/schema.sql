CREATE TABLE IF NOT EXISTS "route"
(
    "id"      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "from"    VARCHAR NOT NULL,
    "to"      VARCHAR NOT NULL
);