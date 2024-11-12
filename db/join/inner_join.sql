create table boxing_organizations(
	id serial primary key,
	name varchar(255),
	country varchar(255)
);
create table boxers(
	id serial primary key,
	name varchar(255),
	age int,
	boxing_organizations_id int references boxing_organizations(id)
);
insert into boxing_organizations(name, country) values ('IBO', 'USA');
insert into boxing_organizations(name, country) values ('IBF', 'USA');
insert into boxing_organizations(name, country) values ('WBA', 'Panama');
insert into boxing_organizations(name, country) values ('WBC', 'Mexico');
insert into boxing_organizations(name, country) values ('WBO', 'Puerto Rico');
insert into boxers(name, age, boxing_organizations_id) values(
	'Mike Tyson', 35, 3
);
insert into boxers(name, age, boxing_organizations_id) values(
	'Alexander Povetkin', 32, 4
);
insert into boxers(name, age, boxing_organizations_id) values(
	'Tyson Fury', 29, 5
);
insert into boxers(name, age, boxing_organizations_id) values(
	'Marco Barrera', 24, 2
);
insert into boxers(name, age, boxing_organizations_id) values(
	'David Benavidez', 28, 1
);
select b.name Имя, b.age Возраст, bo.name Организация from boxers b
join boxing_organizations bo on b.boxing_organizations_id = bo.id;
select b.name Имя, b.age Возраст, bo.name Организация, bo.country from boxers b
join boxing_organizations bo on b.boxing_organizations_id = bo.id
where bo.name = 'WBO';
select b.name Имя, b.age Возраст, bo.name Организация from boxers b
join boxing_organizations bo on b.boxing_organizations_id = bo.id
where b.name LIKE '%Tyson%';