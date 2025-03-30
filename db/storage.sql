create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);



create or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price * 1.2
    where id = (select id from inserted);
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create or replace trigger tax_trigger
    after insert
    on products
    referencing new table as
        inserted
    for each statement
execute procedure tax();



create or replace function tax_row()
    returns trigger as
$$
BEGIN
    new.price = new.price * 1.2;
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create or replace trigger tax_trigger_row
    before insert
    on products
    for each row
execute procedure tax_row();



create or replace function log_price_history()
    returns trigger as
$$
BEGIN
    insert into history_of_price (name, price, date)
    values (new.name, new.price, current_timestamp);
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create or replace trigger log_price_history
    after insert
    on products
    for each row
execute procedure log_price_history();



insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 5, 100);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 10, 200);
