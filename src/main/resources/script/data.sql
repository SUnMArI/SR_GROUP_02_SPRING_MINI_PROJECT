CREATE TABLE users (
    user_id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255)
);

CREATE TABLE categories(
    category_id UUID PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(255),
    user_id UUID,
    CONSTRAINT users_tb_user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE opts (
    opt_id UUID PRIMARY KEY,
    opt_code VARCHAR(255) NOT NULL,
    issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiration TIMESTAMP NOT NULL,
    verify BOOLEAN DEFAULT FALSE,
    user_id UUID NOT NULL ,
    CONSTRAINT users_tb_user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE expenses (
    expense_id UUID PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    date DATE NOT NULL,
    user_id UUID NOT NULL ,
    CONSTRAINT users_tb_user_id FOREIGN KEY(user_id) REFERENCES users(user_id),
    category_id UUID NOT NULL ,
    CONSTRAINT categories_tb_category_id FOREIGN KEY(category_id) REFERENCES categories(category_id)
);