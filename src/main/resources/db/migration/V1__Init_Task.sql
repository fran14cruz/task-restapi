CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table TASK (
  id uuid default uuid_generate_v1 (),
  status varchar(50),
  date varchar(255),
  primary key (id)
);