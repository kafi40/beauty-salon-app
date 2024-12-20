DROP TABLE IF EXISTS "users";
DROP TABLE IF EXISTS "positions";
DROP TABLE IF EXISTS "images";

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
    avatar_id BIGINT,
    role VARCHAR(32),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS positions(
    id SERIAL NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    min_salary DOUBLE PRECISION NOT NULL,
    max_salary DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS images(
    id SERIAL NOT NULL UNIQUE,
    path VARCHAR(512) NOT NULL,
    title VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT "users_position_id" FOREIGN KEY (position_id) REFERENCES positions(id);
ALTER TABLE users ADD CONSTRAINT "users_avatar_id" FOREIGN KEY (avatar_id) REFERENCES images(id);