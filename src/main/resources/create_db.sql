CREATE TABLE profile(
    id INT NOT NULL auto_increment, 
    fullname VARCHAR(80) NOT NULL,
    site VARCHAR(256) NOT NULL,
    storyurl VARCHAR(256) NOT NULL,
    isperson boolean NOT NULL UNIQUE,
    twitteruri VARCHAR(256) NOT NULL,
    
    PRIMARY KEY (id)
);