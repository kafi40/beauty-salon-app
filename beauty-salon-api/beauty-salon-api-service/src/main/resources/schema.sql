CREATE TABLE IF NOT EXISTS users(
    id SERIAL NOT NULL UNIQUE,
    first_name VARCHAR(32) NOT NULL,
    last_name VARCHAR(32),
    middle_name VARCHAR(32),
    email VARCHAR(254) NOT NULL UNIQUE,
    gender VARCHAR(12),
    birthday DATE,
    telegram_id BIGINT UNIQUE,
    position_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS positions(
    id SERIAL NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    min_salary DOUBLE PRECISION NOT NULL,
    max_salary DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
);