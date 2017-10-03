CREATE TABLE useraccount (
	id int8 NOT NULL,
	username varchar(100) NULL,
	password varchar(100) NULL,
	"version" int8 NOT NULL,
	UNIQUE(username),
	CONSTRAINT useraccount_pk PRIMARY KEY (id)
);

CREATE SEQUENCE useraccount_seq
INCREMENT BY 50
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;
