insert into roles(name) values('Admin');
insert into users(name, roles_id) values('Ivan', 1);
insert into rules(name) values('full access');
insert into roles_rules(roles_id, rules_id) values(1,1);
insert into categories(name) values('equipment');
insert into states(name) values('waiting');
insert into items(name, user_id, category_id, state_id) values(
	'video card', 1, 1, 1
);
insert into comments (name, item_id) values ('urgently needed', 1);
insert into attachs(name, item_id) values ('photo.jpg', 1);