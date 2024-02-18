BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, to_do_list, to_do_item CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE to_do_list
(
   list_id SERIAL,
   title VARCHAR(64) NOT NULL,
   description VARCHAR(300),
   CONSTRAINT PK_to_do_list PRIMARY KEY(list_id)
);

CREATE TABLE to_do_item
(
   item_id SERIAL,
   list_id int NOT NULL,
   title VARCHAR(64) NOT NULL,
   description VARCHAR(300),
   CONSTRAINT PK_to_do_item PRIMARY KEY(item_id),
   CONSTRAINT FK_to_do_item FOREIGN KEY(list_id) REFERENCES to_do_list(list_id)
);

-- CREATE INSERTS TO POPULATE WITH ANY STARTING DATA
--populate to_do_list table
INSERT INTO to_do_list (title, description) VALUES ('Monday', 'Cleaning Chores');
INSERT INTO to_do_list (title, description) VALUES ('Computer', 'Parts to Upgrade');
INSERT INTO to_do_list (title, description) VALUES ('Skateboarding', 'Tricks to Remember');

--populate the to_do_item table
INSERT INTO to_do_item (list_id, title, description) VALUES (1, 'Dusting', 'TV Stand, Night Stand');
INSERT INTO to_do_item (list_id, title, description) VALUES (2, 'GPU', '4090 Super');
INSERT INTO to_do_item (list_id, title, description) VALUES (3, 'Kickflip', 'Foot Placement');

COMMIT TRANSACTION;
