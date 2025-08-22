INSERT INTO users (username, password, email) VALUES ('Michael', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'michael@example.com');
INSERT INTO users (username, password, email) VALUES ('Aaron', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'aaron@example.com');
INSERT INTO users (username, password, email) VALUES ('Admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'admin@example.com');

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1,2);
INSERT INTO user_roles (user_id, role_id) VALUES (2,2);
INSERT INTO user_roles (user_id, role_id) VALUES (3,1);

INSERT INTO authorities (name) VALUES ('READ');
INSERT INTO authorities (name) VALUES ('WRITE');
INSERT INTO authorities (name) VALUES ('UPDATE');
INSERT INTO authorities (name) VALUES ('DELETE');

INSERT INTO role_authorities (role_id, authority_id) VALUES (1,1);
INSERT INTO role_authorities (role_id, authority_id) VALUES (1,2);
INSERT INTO role_authorities (role_id, authority_id) VALUES (1,3);
INSERT INTO role_authorities (role_id, authority_id) VALUES (1,4);
INSERT INTO role_authorities (role_id, authority_id) VALUES (2,1);
INSERT INTO role_authorities (role_id, authority_id) VALUES (2,2);