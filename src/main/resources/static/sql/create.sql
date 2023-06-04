DROP TABLE IF EXISTS bad_reputation_users;
DROP TABLE IF EXISTS resumes_companies;
DROP TABLE IF EXISTS vacancies_technologies;
DROP TABLE IF EXISTS vacancies_resumes;
DROP TABLE IF EXISTS resumes_technologies;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS resumes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS hr_responses;
DROP TABLE IF EXISTS technologies;
DROP TABLE IF EXISTS vacancies;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS skill_levels;

CREATE TABLE users
(
    id       INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name     VARCHAR(50),
    login    VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE roles
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50)
);

CREATE TABLE categories
(
    id               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_of_category VARCHAR(50)
);

CREATE TABLE skill_levels
(
    id                  INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_of_skill_level VARCHAR(50)
);

CREATE TABLE technologies
(
    id                 INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_of_technology VARCHAR(50)
);

CREATE TABLE hr_responses
(
    id               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_of_response VARCHAR(50)
);

CREATE TABLE resumes
(
    id               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id          INT          REFERENCES users (id),
    name             VARCHAR(50),
    desired_position VARCHAR(50),
    skill_level_id   INT          REFERENCES skill_levels (id),
    desired_salary   FLOAT,
    about            VARCHAR(100),
    category_id      INT REFERENCES categories (id)
);

CREATE TABLE companies
(
    id              INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name_of_company VARCHAR(50)
);

CREATE TABLE vacancies
(
    id                          INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name                        VARCHAR(50),
    about                       VARCHAR(100),
    estimated_salary            FLOAT,
    skill_level_id              INT REFERENCES skill_levels (id),
    required_working_experience INT
);

CREATE TABLE bad_reputation_users
(
    id      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id INT REFERENCES users (id) UNIQUE
);

CREATE TABLE users_roles
(
    id      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id INT REFERENCES users (id),
    role_id INT REFERENCES roles (id)
);

CREATE TABLE resumes_technologies
(
    id            INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    resume_id     INT REFERENCES resumes (id),
    technology_id INT REFERENCES technologies (id)
);

CREATE TABLE resumes_companies
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    resume_id  INT REFERENCES resumes (id),
    company_id INT REFERENCES companies (id)
);

CREATE TABLE vacancies_technologies
(
    id            INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    vacancy_id    INT REFERENCES vacancies (id),
    technology_id INT REFERENCES technologies (id)
);

CREATE TABLE vacancies_resumes
(
    id             INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    vacancy_id     INT REFERENCES vacancies (id),
    resume_id      INT REFERENCES resumes (id),
    hr_response_id INT REFERENCES hr_responses (id)
);

INSERT INTO users(name, login, password)
VALUES ('Никита Пархомчик', 'NiPar', '123456'),
       ('Игорь Простак', 'iprost', 'password'),
       ('Яна Свердлова', 'ysver', '123456'),
       ('Петр Новиков', 'pnovik', 'p@ssw0rd'),
       ('Анастасия Победина', 'apobed', 'pobed123'),
       ('Алексей Зык', 'azyk', '654321'),
       ('Владислав Стреканов', 'vstrekach', '45361'),
       ('Даниил Разумов', 'draz', 'daniil123'),
       ('Мария Алехнович', 'malex', 'MaLeX'),
       ('Роман Победин', 'rpobed', 'qwerty');
INSERT INTO roles(name)
VALUES ('USER'),
       ('HR'),
       ('ADMIN');
INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
INSERT INTO categories(name_of_category)
VALUES ('Перовоочередные'),
       ('Второочередные'),
       ('Последние'),
       ('Отклонено');
INSERT INTO skill_levels(name_of_skill_level)
VALUES ('Стажер'),
       ('Джуниор'),
       ('Мидл'),
       ('Сеньор');
INSERT INTO technologies(name_of_technology)
VALUES ('Java'),
       ('Spring Core'),
       ('Spring Boot'),
       ('Spring Web'),
       ('Spring Rest'),
       ('Spring Data'),
       ('Spring Security'),
       ('Spring Cloud'),
       ('Maven'),
       ('Gradle'),
       ('HTML'),
       ('CSS'),
       ('JavaScript'),
       ('React'),
       ('Node JS'),
       ('TypeScript'),
       ('PHP');
INSERT INTO hr_responses(name_of_response)
VALUES ('Приглашение'),
       ('Отправить тестовое задание'),
       ('Дополнительно собеседование'),
       ('Отказ'),
       ('На рассмотрении');
INSERT INTO resumes(user_id, name, desired_position, skill_level_id, desired_salary, about, category_id)
VALUES (1, 'Разработчик на Java', 'Инженер-программист', 3, 100000,
        'Опытный инженер-программист со знанием Java и Spring Framework', 1),
       (2, 'Джуниор Full-Stack разработчик', 'Full-Stack разработчик', 2, 80000,
        'Разработчик Full Stack с опытом работы с React и Node.js', 1),
       (3, 'UI/UX дизайнер', 'Джуниор UI/UX дизайнер', 2, 75000,
        'UI/UX дизайнер со знанием HTML, CSS и JavaScript', 3),
       (4, 'Администратор баз данных', 'Администратор баз данных', 3, 90000,
        'Опытный администратор базы данных со знанием баз данных Oracle и MySQL.', 2),
       (5, 'Стажер разработчик на Java', 'Инженер-программист', 1, 60000,
        'Стажер-программист со знанием Java и Spring Framework', 4),
       (6, 'Front-End разработчик', 'Джуниор Front-End разработчик', 2, 75000,
        'Опытный фронтенд-разработчик со знанием React и TypeScript', 2),
       (7, 'Разработчик на Java', 'Инженер-программист', 3, 95000,
        'Опытный разработчик программного обеспечения со знанием Java и Spring Framework', 1),
       (8, 'Back End разработчик', 'Джуниор Back End разработчик', 2, 85000,
        'Опытный бэкенд-разработчик с опытом работы с Node.js и TypeScript', 3),
       (9, 'Веб разработчик', 'Джуниор веб разработчик', 2, 70000,
        'Опытный веб-разработчик со знанием HTML, CSS и JavaScript', 4),
       (10, 'Java разработчик', 'Джуниор Java разработчик', 2, 50000,
        'Программист со знанием Java и Spring Framework', 4);

INSERT INTO resumes_technologies(resume_id, technology_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (2, 13),
       (2, 14),
       (2, 15),
       (2, 16),
       (2, 17),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (4, 1),
       (4, 10),
       (4, 11),
       (4, 12),
       (5, 1),
       (5, 3),
       (5, 6),
       (6, 13),
       (6, 16),
       (7, 1),
       (7, 2),
       (7, 3),
       (7, 4),
       (7, 5),
       (7, 6),
       (7, 7),
       (8, 15),
       (8, 16),
       (8, 17),
       (9, 11),
       (9, 12),
       (9, 13),
       (9, 14),
       (9, 15),
       (10, 1),
       (10, 3),
       (10, 6);

INSERT INTO vacancies(name, about, estimated_salary, skill_level_id, required_working_experience)
VALUES ('Инженер-программист', 'Открыта вакансия инженера-программиста с опытом работы с Java и Spring Framework', 80000,3, 3),
       ('Front End разработчик', 'Открыта вакансия фронтенд-разработчика с опытом работы с React и Angular.', 70000, 2, 2),
       ('UI/UX дизайнер', 'Открыта вакансия UI/UX дизайнера со знанием Adobe XD и Sketch.', 60000, 2, 3),
       ('Full Stack разработчик', 'Открыта вакансия full stack разработчика с опытом работы с React, Node.js и MongoDB',90000, 3, 4),
       ('Data Scientist', 'Открыта вакансия специалиста по данным с опытом работы с Python и машинным обучением', 100000, 3,5),
       ('Разработчик мобильных приложений', 'Открыта вакансия разработчика мобильных приложений с опытом разработки под Android и iOS.', 80000, 2, 4),
       ('DevOps инженер', 'Открыта вакансия DevOps-инженера с опытом работы в Jenkins, Docker и Kubernetes',95000, 3, 5),
       ('Облачный архитектор', 'Открыта вакансия облачного архитектора с опытом работы в AWS и Azure', 110000, 3, 6),
       ('Бизнес аналитик', 'Открыта вакансия бизнес-аналитика со знаниями в области анализа данных и управления проектами', 70000, 2, 3),
       ('Сетевой инженер', 'Открыта вакансия сетевого инженера с опытом работы в Cisco и Juniper', 85000, 2, 4);

INSERT INTO vacancies_technologies (vacancy_id, technology_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 4),
       (3, 1),
       (3, 3),
       (3, 5),
       (4, 2),
       (4, 3),
       (5, 1),
       (5, 4),
       (6, 2),
       (6, 5),
       (7, 1),
       (7, 2),
       (8, 3),
       (8, 4),
       (9, 1),
       (9, 5);