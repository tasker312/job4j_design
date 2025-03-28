create table type
(
    id   int primary key,
    name varchar(255)
);

create table product
(
    id           int primary key,
    name         varchar(255),
    expired_date date,
    price        decimal(10, 2),
    type_id      int references type (id)
);

select p.id, t.name as type, p.name, p.expired_date, p.price
from product p
         join type t on p.type_id = t.id
where t.name = 'СЫР';

select *
from product
where name like '%мороженое%';

select *
from product
where expired_date < current_date;

select *
from product
order by price desc
limit 1;

select t.name, count(*)
from product p
         join type t on p.type_id = t.id
group by t.id;

select p.id, t.name as type, p.name, p.expired_date, p.price
from product p
         join type t on p.type_id = t.id
where t.name = 'СЫР'
   or t.name = 'МОЛОКО';

select t.name, count(*)
from product p
         join type t on p.type_id = t.id
group by t.id
having count(*) < 10;

select p.id, t.name as type, p.name, p.expired_date, p.price
from product p
         join type t on p.type_id = t.id;
