IF (SCHEMA_ID('old_data') IS NULL) 
BEGIN
EXEC('CREATE SCHEMA old_data;')
END
IF (SCHEMA_ID('new_data') IS NULL) 
BEGIN
EXEC('CREATE SCHEMA new_data;')
END

CREATE TABLE old_data.role (
    role_id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE old_data.person (
    person_id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    ssn VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role_id BIGINT REFERENCES old_data.role,
    username VARCHAR(255)
);

CREATE TABLE old_data.availability (
    availability_id BIGINT PRIMARY KEY,
    person_id BIGINT REFERENCES old_data.person,
    from_date DATE,
    to_date DATE
);

CREATE TABLE old_data.competence (
    competence_id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE old_data.competence_profile (
    competence_profile_id BIGINT PRIMARY KEY,
    person_id BIGINT REFERENCES old_data.person,
    competence_id BIGINT REFERENCES old_data.competence,
    years_of_experience NUMERIC(4,2)
);

INSERT INTO old_data.role (role_id, name) VALUES (1, 'recruit');

INSERT INTO old_data.role (role_id, name) VALUES (2, 'applicant');

INSERT INTO old_data.person (person_id, name, surname, username, password, role_id)
VALUES (1, 'Greta', 'Borg', 'borg', 'wl9nk23a', 1);

INSERT INTO old_data.person (person_id, name, surname, ssn, email, role_id)
VALUES (2, 'Per', 'Strand', '19671212-1211', 'per@strand.kth.se', 2);

INSERT INTO old_data.availability (availability_id, person_id, from_date, to_date)
VALUES (1, 2, '2014-02-23', '2014-05-25');

INSERT INTO old_data.availability (availability_id, person_id, from_date, to_date)
VALUES (2, 2, '2014-07-10', '2014-08-10');

INSERT INTO old_data.competence (competence_id, name)
VALUES (1, 'Korvgrillning');

INSERT INTO old_data.competence (competence_id, name)
VALUES (2, 'Karuselldrift');

INSERT INTO old_data.competence_profile (competence_profile_id, person_id,
competence_id, years_of_experience)
VALUES (1, 2, 1, 3.5);

INSERT INTO old_data.competence_profile (competence_profile_id, person_id,
competence_id, years_of_experience)
VALUES (2, 2, 2, 2.0);

CREATE sequence new_data.hibernate_sequence start with 1 increment by 1;
CREATE table new_data.account (id int NOT NULL, password varchar(255), username varchar(255), person_id int NOT null, primary key (id));
CREATE table new_data.full_name (id int NOT NULL, first_name varchar(255), last_name varchar(255), primary key (id));
CREATE table new_data.person (id int NOT NULL, birth_date DATE NOT null, email varchar(255), name_id int NOT null, primary key (id));
ALTER table new_data.account add constraint FKd9dhia7smrg88vcbiykhofxee foreign key (person_id) references new_data.person;
ALTER table new_data.person add constraint FKeuf72vnuu4dtg9l4wtv97ays0 foreign key (name_id) references new_data.full_name;
