

/* Drop Tables */

DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE Students
(
	id SERIALNOT NULL UNIQUE,
	name varchar(50) NOT NULL,
	email varchar(60) NOT NULL,
	password varchar(20) NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE users
(
	user_id SERIAL NOT NULL UNIQUE,
	name varchar(100) NOT NULL,
	phone_no varchar(20) NOT NULL,
	email varchar(30) NOT NULL,
	mail_status boolean NOT NULL,
	created_date date NOT NULL,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;



