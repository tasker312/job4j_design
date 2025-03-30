create table students
(
    id   serial primary key,
    name varchar(255)
);

-- transaction 1
begin transaction isolation level serializable;
select *
from students;
update students
set name = 'Ivan'
where id = 3;
select *
from students;
commit;

-- transaction 2
begin transaction isolation level serializable;
select *
from students;
update students
set name = 'Petr'
where id = 3;
rollback;
