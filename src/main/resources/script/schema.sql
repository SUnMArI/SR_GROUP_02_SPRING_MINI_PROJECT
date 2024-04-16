CREATE DATABASE Spring_Mini_Porject;

CREATE TABLE Users(
                      user_id SERIAL PRIMARY KEY ,
                      email VARCHAR(50) NOT NULL  UNIQUE ,
                      password VARCHAR(255) NOT NULL ,
                      profile_image VARCHAR(255)
);

CREATE TABLE Otps(
                     opt_id SERIAL PRIMARY KEY,
                     opt_code VARCHAR(6) NOT NULL ,
                     issued_at TIMESTAMP NOT NULL ,
                     expiration TIMESTAMP NOT NULL ,
                     verify BOOLEAN,
                     user_id INT,
                     CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES Otps(opt_id)
                         ON UPDATE CASCADE ON DELETE  SET NULL
);

CREATE TABLE Categories(
                           category_id SERIAL PRIMARY KEY ,
                           name VARCHAR(50) NOT NULL ,
                           description TEXT,
                           user_id INT,
                           CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES Categories(category_id)
                               ON UPDATE CASCADE ON DELETE SET NULL
);


CREATE TABLE Expenses(
                         expense_id SERIAL PRIMARY KEY ,
                         amount DECIMAL(10,2) NOT NULL,
                         description TEXT,
                         date TIMESTAMP NOT NULL,
                         user_id INT,
                         category_id INT,
                         CONSTRAINT FK_user_id_Expense_db FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE ,
                         CONSTRAINT Fk_category_id_Expense_db FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE  CASCADE
);