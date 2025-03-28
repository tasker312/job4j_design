create table body
(
    id   serial primary key,
    name text
);

create table engine
(
    id   serial primary key,
    name text
);

create table transmission
(
    id   serial primary key,
    name text
);

create table car
(
    id        serial primary key,
    body_id   int references body (id)         not null,
    engine_id int references engine (id)       not null,
    trans_id  int references transmission (id) not null
);

insert into body (name)
values ('B1'),
       ('B2'),
       ('B3');

insert into engine (name)
values ('E1'),
       ('E2'),
       ('E3'),
       ('E4'),
       ('E5');

insert into transmission (name)
values ('T1'),
       ('T2');

select c.id,
       b.name as Кузов,
       e.name as Двигатель,
       t.name as "Коробка передач"
from car c
         join body b on c.body_id = b.id
         join engine e on c.engine_id = e.id
         join transmission t on c.trans_id = t.id;

select b.name
from car c
         right join body b on c.body_id = b.id
where c.body_id is null;
select e.name
from car c
         right join engine e on c.engine_id = e.id
where c.body_id is null;
select t.name
from car c
         right join transmission t on c.trans_id = t.id
where c.body_id is null;
