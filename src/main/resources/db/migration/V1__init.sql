create table booking_days (
  id      BIGSERIAL PRIMARY KEY,
  day     date unique not null,
  version bigint
);

create table bookings (
  id          BIGSERIAL PRIMARY KEY,
  day         BIGINT references booking_days (id),
  start_time  time,
  duration    time,
  description varchar,
  notes       varchar,
  ticket      varchar,
  version     bigint
);