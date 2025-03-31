create table students
(
    id   serial primary key,
    name varchar(255),
    age  int
);

insert into students (name, age)
values ('Ivan', 20);
insert into students (name, age)
values ('Petr', 25);

begin transaction;
select *
from students;
update students
set age = 25
where name = 'Ivan';
savepoint save1;
insert into students (name, age)
values ('Egor', 30);
savepoint save2;
insert into students (name, age)
values ('Misha', 30);
select *
from students;
rollback to save2;
select *
from students;
rollback to save1;
commit;
select *
from students;
