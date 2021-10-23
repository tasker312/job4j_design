create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	dep_id int references departments(id)
);

create table teens(
	id serial primary key,
	name text,
	gender char(1)
);

insert into departments (name) values ('Human Resources');
insert into departments (name) values ('Business Development');
insert into departments (name) values ('Engineering');
insert into departments (name) values ('Support');
insert into departments (name) values ('Product Management');
insert into departments (name) values ('Accounting');
insert into departments (name) values ('Research and Development');
insert into departments (name) values ('Sales');
insert into departments (name) values ('Legal');
insert into departments (name) values ('Training');

insert into employees (name, dep_id) values ('Veronica', 1);
insert into employees (name, dep_id) values ('Hestia', 2);
insert into employees (name, dep_id) values ('Walther', 3);
insert into employees (name, dep_id) values ('Guinevere', 1);
insert into employees (name, dep_id) values ('Karl', 9);
insert into employees (name, dep_id) values ('Leesa', 1);
insert into employees (name, dep_id) values ('Aloisia', 4);
insert into employees (name, dep_id) values ('Astrix', 3);
insert into employees (name, dep_id) values ('Ab', 9);
insert into employees (name, dep_id) values ('Jim', 6);
insert into employees (name, dep_id) values ('Martelle', 6);
insert into employees (name, dep_id) values ('Laure', 2);
insert into employees (name, dep_id) values ('Brandie', 7);
insert into employees (name, dep_id) values ('Elston', 8);
insert into employees (name, dep_id) values ('Worthington', 8);
insert into employees (name, dep_id) values ('Lyndy', 1);
insert into employees (name, dep_id) values ('Shane', 2);
insert into employees (name, dep_id) values ('Hillard', 4);
insert into employees (name, dep_id) values ('Nanine', 6);

insert into teens (name, gender) values ('Fredrika', 'f');
insert into teens (name, gender) values ('Angus', 'm');
insert into teens (name, gender) values ('Chloette', 'f');
insert into teens (name, gender) values ('Odo', 'm');
insert into teens (name, gender) values ('Ethelbert', 'm');
insert into teens (name, gender) values ('Callie', 'm');
insert into teens (name, gender) values ('Maribelle', 'f');
insert into teens (name, gender) values ('Dorie', 'f');
insert into teens (name, gender) values ('Hildegaard', 'm');
insert into teens (name, gender) values ('Carmita', 'f');