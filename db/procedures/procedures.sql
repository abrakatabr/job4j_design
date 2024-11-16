create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure delete_data(u_id integer, u_count integer)
language 'plpgsql'
as $$
    BEGIN
		if u_id > 0 THEN
			delete from products
			where id = u_id;
		end if;
		if u_count > 0 THEN
			delete from products
			where count < u_count;
		end if;
    END;
$$;

create
or replace function f_delete_data(u_id integer, u_count integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_id > 0 THEN
            delete from products
            where id = u_id;
            select into result count(name)
            from products;
        end if;
        if u_count > 0 THEN
            delete from products
            where count < u_count;
            select into result sum(count)
            from products;
        end if;
        return result;
    end;
$$;

insert into products (name, producer, count, price) values(
	'ME45', 'Siemens', 10, 100
);
insert into products (name, producer, count, price) values(
	'X50', 'Samsung', 15, 80
);
insert into products (name, producer, count, price) values(
	'XE90', 'Motorolla', 50, 90
);
insert into products (name, producer, count, price) values(
	'Iphone', 'Apple', 25, 150
);
select * from products;

call delete_data(1, 0);
call delete_data(0, 20);
insert into products (name, producer, count, price) values(
	'ME45', 'Siemens', 10, 100
);
insert into products (name, producer, count, price) values(
	'X50', 'Samsung', 15, 80
);
select f_delete_data(5, 0);
select f_delete_data(0, 20);