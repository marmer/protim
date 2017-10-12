create table TODO (
  id SERIAL PRIMARY KEY,
  description varchar(100) NOT NULL,
  created_on timestamp NOT NULL
);
