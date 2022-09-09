DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(100),
  password varchar(100),
  enabled tinyint(1),
  PRIMARY KEY (id)
);
