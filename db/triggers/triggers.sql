create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.25
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create
or replace function tax_for_each()
    returns trigger as
$$
    BEGIN
		NEW.price := NEW.price + NEW.price * 0.25;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

create trigger tax_for_each_trigger
    before insert
    on products
    for each row
    execute procedure tax_for_each();

insert into products (name, producer, count, price) values(
	'ME45', 'Siemens', 10, 100
);
insert into products (name, producer, count, price) values(
	'X50', 'Samsung', 15, 80
);

select * from products;

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function history_log()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date) values (
		NEW.name, NEW.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_trigger
    after insert
    on products
    for each row
    execute procedure history_log();

insert into products (name, producer, count, price) values(
	'XE90', 'Motorolla', 50, 90
);

select * from history_of_price;
select * from products;