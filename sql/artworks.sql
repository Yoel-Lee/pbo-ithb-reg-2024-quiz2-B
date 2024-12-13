CREATE TABLE artworks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL UNIQUE,
    image_path VARCHAR(255) NOT NULL,
    user_id INT NOT NULL, -- 
    FOREIGN KEY (user_id) REFERENCES users(id) 
);
