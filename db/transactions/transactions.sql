create table cars(
	id serial primary key,
	model varchar(255),
	price integer
);

insert into cars(model, price) values('Toyota', 2700000);
insert into cars(model, price) values('KIA', 2200000);
insert into cars(model, price) values('BMW', 4700000);
insert into cars(model, price) values('Honda', 3200000);

begin transaction isolation level serializable;
insert into cars(model, price) values('VW', 3500000);
update cars set price = 3000000 where id = 5;
commit;

begin transaction isolation level serializable;
select sum(price) from cars;
update cars set price = price + 100000 where id = 5;
commit;