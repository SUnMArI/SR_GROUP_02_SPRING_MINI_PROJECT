CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255)
);

CREATE TABLE categories(
    category_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100),
    description VARCHAR(255),
    user_id UUID,
    CONSTRAINT users_tb_user_id FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE opts (
    opt_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    opt_code VARCHAR(255) NOT NULL,
    issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiration TIMESTAMP NOT NULL,
    verify BOOLEAN DEFAULT FALSE,
    user_id UUID NOT NULL ,
    CONSTRAINT users_tb_user_id FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE expenses (
    expense_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    amount DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    date DATE NOT NULL,
    user_id UUID NOT NULL ,
    CONSTRAINT users_tb_user_id FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE ,
    category_id UUID NOT NULL ,
    CONSTRAINT categories_tb_category_id FOREIGN KEY(category_id) REFERENCES categories(category_id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- for enable default value oy UUID function "uuid_generate_v4()"
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";