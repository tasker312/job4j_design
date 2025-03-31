create table products
(
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price)
values ('product_1', 1, 5);
insert into products (name, count, price)
values ('product_2', 2, 10);
insert into products (name, count, price)
values ('product_3', 3, 15);
insert into products (name, count, price)
values ('product_4', 4, 20);
insert into products (name, count, price)
values ('product_5', 5, 25);
insert into products (name, count, price)
values ('product_6', 6, 30);
insert into products (name, count, price)
values ('product_7', 7, 35);
insert into products (name, count, price)
values ('product_8', 8, 40);
insert into products (name, count, price)
values ('product_9', 9, 45);
insert into products (name, count, price)
values ('product_10', 10, 50);
insert into products (name, count, price)
values ('product_11', 11, 55);
insert into products (name, count, price)
values ('product_12', 12, 60);
insert into products (name, count, price)
values ('product_13', 13, 65);
insert into products (name, count, price)
values ('product_14', 14, 70);
insert into products (name, count, price)
values ('product_15', 15, 75);
insert into products (name, count, price)
values ('product_16', 16, 80);
insert into products (name, count, price)
values ('product_17', 17, 85);
insert into products (name, count, price)
values ('product_18', 18, 90);
insert into products (name, count, price)
values ('product_19', 19, 95);
insert into products (name, count, price)
values ('product_20', 20, 100);


begin;
declare c cursor for select *
                     from products;
move forward all from c;
fetch backward from c;
move backward 4 from c;
fetch backward from c;
move backward 7 from c;
fetch backward from c;
move backward 4 from c;
fetch backward from c;
fetch backward from c;
close c;
commit;
