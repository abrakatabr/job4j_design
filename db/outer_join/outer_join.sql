create table departments(
	id serial primary key,
	name varchar(255)
);
create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);
insert into departments(name) values('Москва');
insert into departments(name) values('Нижний Новгород');
insert into departments(name) values('Санкт-Петербург');
insert into departments(name) values('Нижний Новгород');
insert into employees(name, department_id) values('Петров Иван', 1);
insert into employees(name, department_id) values('Есипов Михаил', 1);
insert into employees(name, department_id) values('Коновалов Александр', 1);
insert into employees(name, department_id) values('Кулибин Евгений', 1);
insert into employees(name, department_id) values('Родионова Мария', 2);
insert into employees(name, department_id) values('Иванов Михаил', 2);
insert into employees(name, department_id) values('Рудин Богдан', 2);
insert into employees(name, department_id) values('Фокина Полина', 3);
insert into employees(name, department_id) values('Кац Юлия', 3);
insert into employees(name, department_id) values('Попенченко Юрий', null);

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;

select d.id, d.name from departments d
left join employees e on d.id = e.department_id
where e.name is null;

select e.id, e.name, d.name from employees e
left join departments d on e.department_id = d.id;
select e.id, e.name, d.name from departments d
right join employees e on e.department_id = d.id;

create table teens(
	name varchar(255),
	gender varchar(6)
);

insert into teens(name, gender) values('Маша', 'жен');
insert into teens(name, gender) values('Оля', 'жен');
insert into teens(name, gender) values('Катя', 'жен');
insert into teens(name, gender) values('Петя', 'муж');
insert into teens(name, gender) values('Вася', 'муж');
insert into teens(name, gender) values('Вова', 'муж');
insert into teens(name, gender) values('Саша', 'муж');

select concat(t1.name, '-', t2.name) as "Пара" from
(select name from teens where gender = 'муж') t1
cross join (select name from teens where gender = 'жен') t2;