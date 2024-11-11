create table status(
	id serial primary key,
	importance varchar(255)
);
create table client(
	id serial primary key,
	name varchar(255)
);
create table clients_statuses(
	id serial primary key,
	status_id int references status(id) unique,
	client_id int references client(id) unique
);
insert into status(importance) values('vip');
insert into status(importance) values('ordinary');
insert into client(name) values('Petrov Petr');
insert into client(name) values('Pozharov Alex');
insert into clients_statuses(status_id, client_id) values (1, 2);
insert into clients_statuses(status_id, client_id) values (2, 1);
select * from clients_statuses;