create table fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date) values(
	'tiger', 7300, '01.07.1234'
);
insert into fauna(name, avg_age, discovery_date) values(
	'horse', 9000, '01.05.100'
);
insert into fauna(name, avg_age, discovery_date) values(
	'crocodile', 18000, '23.02.900'
);
insert into fauna(name, avg_age, discovery_date) values(
	'wolf', 7500, '14.11.900'
);
insert into fauna(name, avg_age, discovery_date) values(
	'eagle', 5100, '19.10.1845'
);
insert into fauna(name, avg_age, discovery_date) values(
	'sword fish', 10000, '12.08.1956'
);
select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';