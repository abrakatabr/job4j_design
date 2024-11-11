create table clinic(
	id serial primary key,
	number int,
	district varchar(255)
);
create table person(
	id serial primary key,
	name varchar(255),
	clinic_id int references clinic(id) 
);
insert into clinic(number, district) values (27, 'west');
insert into clinic(number, district) values (21, 'southeastern');
insert into person(name, clinic_id) values('Ivanov Ivan', 1);
insert into person(name, clinic_id) values('Petrov Petr', 2);
select * from person;
select * from clinic where id in (select clinic_id from person);