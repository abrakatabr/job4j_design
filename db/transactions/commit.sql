create table cars(
	id serial primary key,
	model varchar(255),
	price integer
);

insert into cars(model, price) values('Toyota', 2700000);
insert into cars(model, price) values('KIA', 2200000);
insert into cars(model, price) values('BMW', 4700000);
insert into cars(model, price) values('Honda', 3200000);
commit;

select * from cars;

begin;
insert into cars(model, price) values ('FIAT', 2000000);
update cars set price = 3500000 where model = 'KIA';
select * from cars;
rollback;
select * from cars;

begin;
insert into cars(model, price) values ('FIAT', 2000000);
savepoint point_1;
delete from cars where price > 2300000;
select * from cars;
rollback to point_1;
commit;
select * from cars;