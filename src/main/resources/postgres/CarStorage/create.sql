create table body(
	id serial primary key,
	name text
);

create table engine(
	id serial primary key,
	name text
);

create table transmission(
	id serial primary key,
	name text	
);

create table car(
	id serial primary key,
	body_id int references body(id) not null,
	engine_id int references engine(id) not null,
	trans_id int references transmission(id) not null
);