CREATE DATABASE spring_miniproject;

CREATE TABLE IF NOT EXISTS user_tb
(
    user_id       serial primary key,
    email         varchar(100) NOT NULL,
    password      varchar(100) NOT NULL,
    profile_image varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS otps_tb
(
    opt_id     serial primary key,
    opt_code   INTEGER,
    issued_at  TIMESTAMP,

    expiration TIMESTAMP,
    verify     INTEGER DEFAULT 0,
    user_id    INT REFERENCES user_tb (user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS categories_tb
(
    category_id serial PRIMARY KEY,
    name        varchar(50),
    description varchar(200),
    user_id     INT,
    CONSTRAINT category_id_fk_user_id
        foreign key (user_id)
            REFERENCES user_tb (user_id)
            ON DELETE CASCADE ON UPDATE CASCADE
);

