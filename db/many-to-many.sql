create table author
(
    id      serial primary key,
    name    varchar(255),
    surname varchar(255)
);

create table book
(
    id    serial primary key,
    title varchar(255),
    year  int
);

create table author_book
(
    id        serial primary key,
    author_id int references author (id),
    book_id   int references book (id)
);

insert into author (name, surname)
values ('Ivan', 'Petrov');
insert into author (name, surname)
values ('Petr', 'Ivanov');
insert into author (name, surname)
values ('Egor', 'Grishin');

insert into book (title, year)
values ('Java beginning', 2000);
insert into book (title, year)
values ('Java tutorial', 2010);
insert into book (title, year)
values ('Multithreading', 2005);

insert into author_book (author_id, book_id)
values (1, 1);
insert into author_book (author_id, book_id)
values (1, 2);
insert into author_book (author_id, book_id)
values (2, 1);
insert into author_book (author_id, book_id)
values (3, 3);
