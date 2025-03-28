create table student
(
    id   serial primary key,
    name varchar(255),
    age  int
);

insert into student(name, age)
values ('Ivan', 100);

update student
set age = 20;

select *
from student;

delete
from student;
