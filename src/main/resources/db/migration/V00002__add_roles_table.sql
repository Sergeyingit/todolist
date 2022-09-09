DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id int NOT NULL AUTO_INCREMENT,
  user_id int,
  role varchar(25),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) references users(id)
);
create unique index ix_role_user_id on roles (user_id,role);