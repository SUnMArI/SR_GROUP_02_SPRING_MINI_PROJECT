CREATE TABLE IF NOT EXISTS user_tb(
    user_id serial primary key ,
    email varchar(100) NOT NULL ,
    password varchar(100) NOT NULL ,
    profile_image varchar(250) NOT NULL
    );

CREATE TABLE IF NOT EXISTS otps_tb(
    opt_id serial primary key ,
    opt_code INTEGER,
    issued_at TIMESTAMP,
    expiration TIMESTAMP,
    verify INTEGER DEFAULT 0,
    user_id INT REFERENCES user_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE
    );