DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
  id int NOT NULL AUTO_INCREMENT,
  user_id int,
  title varchar(25),
  description varchar(150),
  date DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) references users(id)
);
