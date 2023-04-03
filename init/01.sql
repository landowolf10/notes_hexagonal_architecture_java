CREATE TABLE IF NOT EXISTS users(
	userid INT AUTO_INCREMENT, 
	name VARCHAR(100) NOT NULL, 
    email VARCHAR(50) NOT NULL, 
    password VARCHAR(255) NOT NULL, 
    role VARCHAR(255) NOT NULL, 
    PRIMARY KEY (userid)
);

CREATE TABLE IF NOT EXISTS notes(
	noteid INT AUTO_INCREMENT, 
    userid INT, owner VARCHAR(100) NOT NULL, 
    title VARCHAR(100) NOT NULL, 
    content VARCHAR(500) NOT NULL, 
    PRIMARY KEY (noteid), 
    FOREIGN KEY (userid) REFERENCES users (userid)
);

SELECT * FROM notes;
SELECT * FROM users;