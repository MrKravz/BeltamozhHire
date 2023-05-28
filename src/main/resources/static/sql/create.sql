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
VALUES ('Nikita', 'NiPar', '123456'),
       ('John Doe', 'johndoe', 'password123'),
       ('Jane Smith', 'janesmith', '123456'),
       ('Bob Johnson', 'bjohnson', 'p@ssw0rd'),
       ('Emily Davis', 'edavis', 'davis123'),
       ('Alex Johnson', 'ajohnson', 'alex123'),
       ('Sarah Lee', 'sarahlee', 'password456'),
       ('David Kim', 'dkim', 'david123'),
       ('Maggie Chen', 'maggiec', 'chen123'),
       ('Ryan Patel', 'rpatel', 'patel123');
INSERT INTO roles(name)
VALUES ('USER'),
       ('HR'),
       ('ADMIN');
INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
INSERT INTO categories(name_of_category)
VALUES ('Firstly'),
       ('Secondary'),
       ('Latest'),
       ('RefuseS');
INSERT INTO skill_levels(name_of_skill_level)
VALUES ('Trainee'),
       ('Junior'),
       ('Middle'),
       ('Senior');
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
VALUES ('Accepted'),
       ('Test task sent'),
       ('Additional review'),
       ('Declined'),
       ('Under Consideration');
INSERT INTO resumes(user_id, name, desired_position, skill_level_id, desired_salary, about, category_id)
VALUES (1, 'Nikita Resume', 'Software Engineer', 3, 100000,
        'Experienced software engineer with a strong background in Java and Spring Framework', 1),
       (2, 'John Resume', 'Full Stack Developer', 2, 80000,
        'Experienced Full Stack developer with expertise in React and Node.js', 1),
       (3, 'Jane Resume', 'UI/UX Designer', 2, 75000,
        'Experienced UI/UX designer with proficiency in HTML, CSS, and JavaScript', 3),
       (4, 'Bob Resume', 'Database Administrator', 3, 90000,
        'Experienced database administrator withexpertise in Oracle and MySQL databases', 2),
       (5, 'Emily Resume', 'Software Engineer', 1, 60000,
        'Trainee software engineer with knowledge of Java and Spring Framework', 4),
       (6, 'Alex Resume', 'Front End Developer', 2, 75000,
        'Experienced front end developer with expertise in React and TypeScript', 2),
       (7, 'Sarah Resume', 'Software Developer', 3, 95000,
        'Experienced software developer with expertise in Java and Spring Framework', 1),
       (8, 'David Resume', 'Back End Developer', 2, 85000,
        'Experienced back end developer with expertise in Node.js and TypeScript', 3),
       (9, 'Maggie Resume', 'Web Developer', 2, 70000,
        'Experienced web developer with proficiency in HTML, CSS, and JavaScript', 4),
       (10, 'Ryan Resume', 'Junior Software Developer', 1, 50000,
        'Trainee software developer with knowledge of Java and Spring Framework', 4);

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
VALUES ('Software Engineer', 'Job opening for a software engineer with experience in Java and Spring Framework', 80000,
        3, 3),
       ('Front End Developer', 'Job opening for a front end developer with expertise in React and Angular', 70000, 2,
        2),
       ('UI/UX Designer', 'Job opening for a UI/UX designer with proficiency in Adobe XD and Sketch', 60000, 2, 3),
       ('Full Stack Developer', 'Job opening for a full stack developer with experience in React, Node.js, and MongoDB',
        90000, 3, 4),
       ('Data Scientist', 'Job opening for a data scientist with expertise in Python and machine learning', 100000, 3,
        5),
       ('Mobile App Developer', 'Job opening for a mobile app developer with experience in Android and iOS development',
        80000, 2, 4),
       ('DevOps Engineer', 'Job opening for a DevOps engineer with experience in Jenkins, Docker, and Kubernetes',
        95000, 3, 5),
       ('Cloud Architect', 'Job opening for a cloud architect with expertise in AWS and Azure', 110000, 3, 6),
       ('Business Analyst',
        'Job opening for a business analyst with proficiency in data analysis and project management', 70000, 2, 3),
       ('Network Engineer', 'Job opening for a network engineer with experience in Cisco and Juniper', 85000, 2, 4);

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