create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

select *
from fauna
where name like '%fish%';

select *
from fauna
where avg_age >= 10000
  and avg_age <= 21000;

select *
from fauna
where discovery_date is null;

select *
from fauna
where discovery_date < '01.01.1950';
