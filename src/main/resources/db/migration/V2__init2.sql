CREATE TABLE sample_model2 (
	id int8 NOT NULL,
	nice_property varchar(100) NULL,
	"version" int8 NOT NULL,
	CONSTRAINT sample_model2_pk PRIMARY KEY (id)
);

CREATE SEQUENCE sample_model_seq2
INCREMENT BY 50
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;
