create table booking_day (
  id      BIGSERIAL PRIMARY KEY,
  day     date unique not null,
  version bigint
);

create table booking (
  id          BIGSERIAL PRIMARY KEY,
  day         BIGINT NOT NULL references booking_day (id),
  start_time  time,
  duration    time,
  description varchar,
  notes       varchar,
  ticket      varchar,
  version     bigint
);