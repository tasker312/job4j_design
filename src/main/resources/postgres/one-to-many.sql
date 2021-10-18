create table client(
	id serial primary key,
	name varchar(255),
	phone varchar(255)
);

create table orders(
	id serial primary key,
	client_id int references client(id),
	sum int
);

insert into client (name, phone) values ('Ivan', '88005553535');
insert into orders (client_id, sum) values (1, 5000);