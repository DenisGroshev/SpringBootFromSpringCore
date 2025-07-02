-- Вставка компаний
INSERT INTO company (id, name)
VALUES (1, 'Google'),
       (2, 'Meta'),
       (3, 'Amazon'),
       (4, 'Microsoft'),
       (5, 'Apple');
SELECT SETVAL('company_id_seq', (SELECT MAX(ID) FROM company));


-- Вставка локалей компаний
INSERT INTO company_locales (company_id, lang, description)
VALUES ((SELECT id FROM company WHERE name = 'Google'), 'en', 'Google description'),
       ((SELECT id FROM company WHERE name = 'Google'), 'ru', 'Google описание'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'en', 'Meta description'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'ru', 'Meta описание'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'en', 'Amazon description'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'ru', 'Amazon описание'),
       ((SELECT id FROM company WHERE name = 'Microsoft'), 'en', 'Microsoft description'),
       ((SELECT id FROM company WHERE name = 'Apple'), 'en', 'Apple description');

-- Вставка пользователей
INSERT INTO users (id, birth_date, firstname, lastname, role, username, company_id)
VALUES (1, '1990-01-10', 'Ivan', 'Ivanov', 'ADMIN', 'ivan@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       (2, '1995-10-19', 'Petr', 'Petrov', 'USER', 'petr@gmail.com', (SELECT id FROM company WHERE name = 'Meta')),
       (3, '2001-12-23', 'Sveta', 'Svetikova', 'USER', 'sveta@gmail.com', (SELECT id FROM company WHERE name = 'Amazon')),
       (4, '1984-03-14', 'Vlad', 'Vladikov', 'USER', 'vlad@gmail.com', (SELECT id FROM company WHERE name = 'Microsoft')),
       (5, '1984-03-14', 'Kate', 'Smith', 'ADMIN', 'kate@gmail.com', (SELECT id FROM company WHERE name = 'Apple'));

SELECT SETVAL('users_id_seq', (SELECT MAX(ID) FROM users));


-- Вставка платежей
INSERT INTO payment (amount, receiver_id)
VALUES (100, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (250, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (400, (SELECT id FROM users WHERE username = 'sveta@gmail.com'));

-- Вставка чатов
INSERT INTO chat (name)
VALUES ('General Chat'),
       ('Tech Discussion'),
       ('Management'),
       ('Support');

-- Вставка связей пользователей с чатами
INSERT INTO users_chat (user_id, chat_id)
VALUES ((SELECT id FROM users WHERE username = 'ivan@gmail.com'), (SELECT id FROM chat WHERE name = 'General Chat')),
       ((SELECT id FROM users WHERE username = 'ivan@gmail.com'), (SELECT id FROM chat WHERE name = 'Management')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'General Chat')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'Tech Discussion')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE name = 'Tech Discussion')),
       ((SELECT id FROM users WHERE username = 'vlad@gmail.com'), (SELECT id FROM chat WHERE name = 'Support')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'Management'));