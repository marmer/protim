create table booking_day (
  id  BIGSERIAL PRIMARY KEY,
  day date unique not null 
);

create table booking (
  id          BIGSERIAL PRIMARY KEY,
  day         BIGINT NOT NULL,
  start_time  time,
  duration    time,
  description varchar,
  notes       varchar,
  ticket      varchar,
  FOREIGN KEY (day) REFERENCES booking_day (id)
);