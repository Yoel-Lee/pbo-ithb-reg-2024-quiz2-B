
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


INSERT INTO users (name, email, password) VALUES
('yoel', 'yoel@gmail.com', '123'),
('bob', 'bob@gmail.com', 'bob1');

