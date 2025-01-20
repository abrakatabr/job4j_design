CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name) VALUES (1, 'OZON'), (2, 'Exist'), (3, 'Yandex Market'),
(4, 'Sbermegamarket'), (5, 'Wildberries');
INSERT INTO person(id, name, company_id) VALUES
(1, 'Alex', 1), (2, 'Nikolay', 1), (3, 'Petr', 1),
(4, 'Irina', 1), (5, 'Vasiliy', 1), (6, 'Kirill', 2),
(7, 'Galina', 2), (8, 'Ostap', 3), (9, 'Valentina', 3),
(10, 'Raisa', 3), (11, 'Stas', 3), (12, 'Vlad', 3),
(13, 'Semen', 3), (14, 'Vladimir', 4), (15, 'Elena', 5),
(16, 'Egor', 5), (17, 'Rustam', 5), (18, 'Grigoriy', 5), (19, 'Elvis', 1);

SELECT p.name person_name, c.name company_name FROM company c
JOIN person p on c.id = p.company_id
WHERE c.id != 5;

SELECT c.name company_name, count(p.id) person_count FROM company c
JOIN person p on c.id = p.company_id
GROUP BY c.name
HAVING count(p.id) = (SELECT max(count) FROM (SELECT company_id, count(id) FROM person
GROUP BY company_id));