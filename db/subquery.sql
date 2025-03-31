create table customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);
create table orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);



insert into customers (first_name, last_name, age, country)
values ('Ivan', 'Ivanov', 20, 'Russia');
insert into customers (first_name, last_name, age, country)
values ('Enrich', 'Zemov', 27, 'Germany');
insert into customers (first_name, last_name, age, country)
values ('Egor', 'Petrov', 27, 'Russia');
insert into customers (first_name, last_name, age, country)
values ('Boris', 'Ponoy', 20, 'Italy');

insert into orders (amount, customer_id)
values (1, 1);
insert into orders (amount, customer_id)
values (5, 1);
insert into orders (amount, customer_id)
values (3, 2);
insert into orders (amount, customer_id)
values (2, 1);



select *
from customers
where age = (select min(age) from customers);

select *
from customers
where id not in (select distinct customer_id from orders);


