create table booking_days (
  id      bigserial primary key,
  day     date not null,
  version bigint
);

create table bookings (
  id          bigserial primary key,
  day         BIGINT references booking_days (id),
  start_time  time,
  duration    time,
  description varchar,
  notes       varchar,
  ticket      varchar,
  version     bigint
);

create table users (
  id                 bigserial primary key,
  username           varchar unique,
  password           varchar,
  enabled            boolean,
  version            bigint,
  created_by         varchar,
  created_date       timestamp,
  last_modified_by   varchar,
  last_modified_date timestamp
);

create table roles (
  id      bigserial primary key,
  user    bigint references users (id),
  name    varchar,
  version bigint
);