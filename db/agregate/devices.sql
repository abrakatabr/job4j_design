create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);
create table people
(
    id   serial primary key,
    name varchar(255)
);
create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people(name) values('Alex');
insert into people(name) values('Oleg');
insert into people(name) values('Petr');
insert into people(name) values('Ivan');
insert into people(name) values('Semen');
insert into devices(name, price) values('Iphone 10', 15000.00);
insert into devices(name, price) values('Apple watch', 10000.00);
insert into devices(name, price) values('Xiaomi miBand3', 1500.00);
insert into devices(name, price) values('Ipod nano', 3000.00);
insert into devices(name, price) values('Samsung TAB', 12000.00);
insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(1, 3);
insert into devices_people(device_id, people_id) values(1, 5);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(2, 4);
insert into devices_people(device_id, people_id) values(2, 5);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(3, 5);
insert into devices_people(device_id, people_id) values(4, 3);
insert into devices_people(device_id, people_id) values(5, 4);
insert into devices_people(device_id, people_id) values(5, 5);
insert into devices_people(device_id, people_id) values(5, 1);

select avg(price) as "Средняя цены всех устройств" from devices;

select p.name, avg(d.price) from people p
join devices_people dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name
order by p.name asc;

select p.name, avg(d.price) from people p
join devices_people dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 10000
order by p.name asc;
