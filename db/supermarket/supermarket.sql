create table type(
	id serial primary key,
	name varchar(255)
);
create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('СМЕТАНА');
insert into type(name) values('КОЛБАСА');
insert into type(name) values('ХЛЕБ');
insert into type(name) values('ШОКОЛАД');
insert into type(name) values('МАЙОНЕЗ');
insert into product(name, type_id, expired_date, price) values(
	'Сыр российский', 1, '20.11.2024', 200.00
);
insert into product(name, type_id, expired_date, price) values(
	'Сыр Hollandia', 1, '22.11.2024', 280.00
);
insert into product(name, type_id, expired_date, price) values(
	'Сыр Arla Natura', 1, '16.11.2024', 399.00
);
insert into product(name, type_id, expired_date, price) values(
	'Молоко Домик в деревне', 2, '14.11.2024', 89.00
);
insert into product(name, type_id, expired_date, price) values(
	'Молоко Простоквашино', 2, '11.11.2024', 99.00
);
insert into product(name, type_id, expired_date, price) values(
	'Сметана Parmalat', 3, '12.11.2024', 121.99
);
insert into product(name, type_id, expired_date, price) values(
	'Сметана Вкуснотеево', 3, '20.11.2024', 75.99
);
insert into product(name, type_id, expired_date, price) values(
	'Сметана Домик в деревне', 3, '14.11.2024', 99.99
);
insert into product(name, type_id, expired_date, price) values(
	'Колбаса сервелат', 4, '01.02.2025', 799.99
);
insert into product(name, type_id, expired_date, price) values(
	'Колбаса докторская', 4, '25.11.2024', 345.00
);
insert into product(name, type_id, expired_date, price) values(
	'Колбаса охотничья', 4, '30.11.2024', 420.00
);
insert into product(name, type_id, expired_date, price) values(
	'Хлеб батон белый', 5, '16.11.2024', 56.00
);
insert into product(name, type_id, expired_date, price) values(
	'Хлеб черный ржаной', 5, '16.11.2024', 52.00
);
insert into product(name, type_id, expired_date, price) values(
	'Шоколад Ritter sport', 6, '01.10.2025', 189.99
);
insert into product(name, type_id, expired_date, price) values(
	'Шоколад Milka подарочный', 6, '01.12.2025', 799.99
);
insert into product(name, type_id, expired_date, price) values(
	'Шоколад Alpine gold вкус сливочное мороженое', 6, '01.12.2025', 150.00
);
insert into product(name, type_id, expired_date, price) values(
	'Майонез Оливковый', 7, '01.06.2025', 185.00
);
insert into product(name, type_id, expired_date, price) values(
	'Майонез Провансаль', 7, '01.06.2025', 162.00
);

select p.name, t.name from product p
join type t on p.type_id = t.id
where t.name = 'СЫР';

select name from product where name like '%мороженое%';

select name, expired_date from product where expired_date < current_date;

select name, price from product where price = (
	select max(price) from product
);

select t.name, count(p.name) from type t
join product p on t.id = p.type_id
group by t.name;

select t.name, p.name from type t
join product p on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.name) from type t
join product p on t.id = p.type_id
group by t.name
having count(p.name) < 3;

select t.name, p.name from type t
join product p on t.id = p.type_id
order by t.name;
