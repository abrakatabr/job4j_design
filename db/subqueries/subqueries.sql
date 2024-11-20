CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);
CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);
insert into customers values 
	(1, 'Alex', 'Pozharov', 37, 'Russia'),
	(2, 'Ivan', 'Petrov', 32, 'Belorussia'),
	(3, 'Petr', 'Lindman', 54, 'Germany'),
	(4, 'Hose', 'Corales', 21, 'Spain'),
	(5, 'Selvodoro', 'Belucci', 28, 'Italy');
insert into orders values
	(1, 2, 1), (2, 3, 3), (3, 1, 5);

select * from customers c where c.id not in (
	select o.customer_id from orders o
	where amount is not null and o.customer_id = c.id);
commit;