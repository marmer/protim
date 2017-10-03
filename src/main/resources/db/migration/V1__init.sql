CREATE TABLE sample_model (
	id int8 NOT NULL,
	nice_property varchar(100) NULL,
	"version" int8 NOT NULL,
	CONSTRAINT sample_model_pk PRIMARY KEY (id)
);

CREATE SEQUENCE sample_model_seq
INCREMENT BY 50
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;