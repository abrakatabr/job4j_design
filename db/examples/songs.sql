create table songs(
	id serial primary key,
	title text,
	singer varchar(255),
	duration time
);
insert into songs(title, singer, duration) values(
	'Burning Love',
	'Elvis Presley',
	'00:02:58'
);
update songs set duration = '00:03:23';
delete from songs;
