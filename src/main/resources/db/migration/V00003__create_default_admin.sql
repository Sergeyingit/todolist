USE tdl_db;

INSERT INTO users (email,password,enabled) VALUES ("admin@admin.com", "$2a$10$NtKRP4n8RMiUO8uxUsEDVenO4TDHm7R5NuyZ9IM8uvho9vdnv07ya", true);
INSERT INTO roles (user_id, role) VALUES (1, "ROLE_ADMIN");
INSERT INTO roles (user_id, role) VALUES (1, "ROLE_USER");