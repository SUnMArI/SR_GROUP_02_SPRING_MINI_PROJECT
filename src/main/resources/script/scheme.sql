CREATE TABLE users(
                      user_id SERIAL  PRIMARY KEY ,
                      email VARCHAR(100),
                      password VARCHAR(100),
                      profile_image VARCHAR(100)
);

CREATE TABLE Otps (
                      opt_id SERIAL  PRIMARY KEY,
                      opt_code int ,
                      issued_at TIMESTAMP  NOT NULL,
                      expriation TIMESTAMP NOT NULL,
                      verify BOOLEAN,
                      user_id INT ,
                      CONSTRAINT fk_userID_otps_tb FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Categories (
                            category_id SERIAL  PRIMARY KEY,
                            name VARCHAR(100),
                            description TEXT ,
                            user_id INT ,
                            CONSTRAINT fk_userID_otps_tb FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Expenses(
                         expense_id SERIAL  PRIMARY KEY,
                         amount NUMERIC(10,2),
                         description TEXT ,
                         date TIMESTAMP ,
                         user_id INT,
                         category_id INT,
                         CONSTRAINT fk_userId_Expenses_tb FOREIGN KEY(user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE SET NULL,
                         CONSTRAINT fk_category_Expenses_tb FOREIGN KEY(category_id) REFERENCES categories(category_id) ON UPDATE CASCADE ON DELETE CASCADE
);



-- Inserting sample data into the users table
    INSERT INTO users (email, password, profile_image)
VALUES
  ('user1@example.com', 'password1', 'profile1.jpg'),
  ('user2@example.com', 'password2', 'profile2.jpg');

-- Inserting sample data into the Otps table
INSERT INTO Otps (opt_code, issued_at, expriation, verify, user_id)
VALUES
    (123456, NOW(), NOW() + INTERVAL '1 hour', FALSE, 1),
    (654321, NOW(), NOW() + INTERVAL '1 hour', FALSE, 2);

-- Inserting sample data into the Categories table
INSERT INTO Categories (name, description, user_id)
VALUES
    ('Category 1', 'Description for category 1', 1),
    ('Category 2', 'Description for category 2', 2);

-- Inserting sample data into the Expenses table
INSERT INTO Expenses (amount, description, date, user_id, category_id)
VALUES
    (100.50, 'Expense description 1', NOW(), 1, 1),
    (200.75, 'Expense description 2', NOW(), 2, 2)
;





SELECT * FROM users WHERE email = 'sopheak098@gmail.com';

