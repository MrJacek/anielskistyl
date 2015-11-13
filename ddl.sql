CREATE TABLE adres
(
    id SERIAL PRIMARY KEY NOT NULL,
    ulica VARCHAR(255) NOT NULL,
    numer VARCHAR(10) NOT NULL,
    miasto VARCHAR(255) NOT NULL,
    kod_pocztowy VARCHAR(32) NOT NULL
);
CREATE TABLE adres_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE kontrachent
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL
);
CREATE TABLE kontrachent_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
