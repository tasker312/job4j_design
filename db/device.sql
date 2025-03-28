create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

select avg(price)
from devices;

select p.name as Имя, avg(d.price) as "Средняя стоимость"
from devices_people dp
         join devices d on dp.device_id = d.id
         join people p on dp.people_id = p.id
group by p.name;

select p.name as Имя, avg(d.price) as "Средняя стоимость"
from devices_people dp
         join devices d on dp.device_id = d.id
         join people p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;

