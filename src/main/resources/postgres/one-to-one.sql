create table medpolicy(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    medpolicy_id int references medpolicy(id) unique
);

insert into medpolicy (seria, number) values (3712, 831288155);
insert into people (name, medpolicy_id) values ('Ivan', 1);