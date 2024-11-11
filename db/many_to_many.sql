create table sport_section(
	id serial primary key,
	name varchar(255)
);
create table student(
	id serial primary key,
	name varchar(255)
);
create table sports_sections_students(
	id serial primary key,
	sport_section_id int references sport_section(id),
	student_id int references student(id)
);
insert into sport_section(name) values ('boxing');
insert into sport_section(name) values ('swimming');
insert into sport_section(name) values ('basketball');
insert into student(name) values ('Petrov Petr');
insert into student(name) values ('Ivanov Ivan');
insert into student(name) values ('Sidorov Alex');
insert into sports_sections_students(sport_section_id, student_id) values (1, 1);
insert into sports_sections_students(sport_section_id, student_id) values (1, 2);
insert into sports_sections_students(sport_section_id, student_id) values (1, 3);
insert into sports_sections_students(sport_section_id, student_id) values (2, 1);
insert into sports_sections_students(sport_section_id, student_id) values (2, 2);
insert into sports_sections_students(sport_section_id, student_id) values (3, 2);
select * from sports_sections_students;