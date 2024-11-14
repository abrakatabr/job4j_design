create table students
(
    id   serial primary key,
    name varchar(50)
);

insert into students (name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');

create table authors
(
    id   serial primary key,
    name varchar(50)
);

insert into authors (name)
values ('Александр Пушкин');
insert into authors (name)
values ('Николай Гоголь');

create table books
(
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id)
values ('Евгений Онегин', 1);
insert into books (name, author_id)
values ('Капитанская дочка', 1);
insert into books (name, author_id)
values ('Дубровский', 1);
insert into books (name, author_id)
values ('Мертвые души', 2);
insert into books (name, author_id)
values ('Вий', 2);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);

create view authors_order_which_more_than_2
as
select a.name authors, count(o.book_id) as count_books from authors a
join books b on a.id = b.author_id
join orders o on b.id = o.book_id
join students s on o.student_id = s.id
group by a.name
having count(o.book_id) > 2;

select * from authors_order_which_more_than_2;