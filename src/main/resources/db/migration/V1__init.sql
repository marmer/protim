create table booking_day (
  id  BIGSERIAL PRIMARY KEY,
  day date unique
);

create table booking (
  id           BIGSERIAL PRIMARY KEY,
  day          BIGINT NOT NULL,
  start_time   time,
  length_in_ms bigint,
  FOREIGN KEY (day) REFERENCES booking_day (id)
);