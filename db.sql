DROP SCHEMA IF EXISTS `progetto_ispw`;
CREATE SCHEMA `progetto_ispw`;
USE `progetto_ispw`; 

CREATE TABLE `CITIZEN_USERS`
(
	username VARCHAR(30) PRIMARY KEY,
    pwd CHAR(32),
	first_name VARCHAR(30),
    surname VARCHAR(30),
	profile_picture BLOB
);

CREATE TABLE `COMPANY_USERS`
(
	username VARCHAR(30) PRIMARY KEY,
    pwd CHAR(32),
	category VARCHAR(30),
    interested_area VARCHAR(30)
);

DROP VIEW IF EXISTS BASE_USER;
CREATE VIEW BASE_USER 
AS
(SELECT C.username AS username, C.pwd AS pwd
FROM CITIZEN_USERS C)
UNION
(SELECT COM.username AS username, COM.pwd AS pwd
FROM COMPANY_USERS COM)
ORDER BY username;

CREATE TABLE `VOLUNTEERING_EVENTS`
(
    id_event INTEGER PRIMARY KEY AUTO_INCREMENT,
	creation_date DATE,
    event_date DATE
);

CREATE TABLE `PARTICIPATIONS`
(
	id_event INTEGER,
    username VARCHAR(30),
	FOREIGN KEY(username) REFERENCES CITIZEN_USERS(username),
    FOREIGN KEY(id_event) REFERENCES VOLUNTEERING_EVENTS(id_event),
    PRIMARY KEY(id_event, username)
);

CREATE TABLE `JOBS`
(
    id_job INTEGER PRIMARY KEY AUTO_INCREMENT,
	start_date DATE,
	end_date DATE,
    state ENUM('A','R','E'), -- Accepted, Refused, Ended
    job_info BLOB
);

CREATE TABLE `COMMUNITY_REPORTS`
(
	id_report INTEGER PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(30),
	full_description VARCHAR(90),
    date_submission DATE,
    address VARCHAR(60),
	latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    submitter VARCHAR(30),
    image BLOB,
    related_event INTEGER,
	FOREIGN KEY(submitter) REFERENCES CITIZEN_USERS(username),
    FOREIGN KEY(related_event) REFERENCES VOLUNTEERING_EVENTS(id_event)
);

CREATE TABLE `COMPANY_REPORTS`
(
	id_report INTEGER PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(30),
	full_description VARCHAR(90),
    date_submission DATE,
    address VARCHAR(60),
	latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    submitter VARCHAR(30),
    image BLOB,
    related_company VARCHAR(30),
    related_job INTEGER,
	FOREIGN KEY(related_company) REFERENCES COMPANY_USERS(username),
	FOREIGN KEY(submitter) REFERENCES CITIZEN_USERS(username),
    FOREIGN KEY(related_job) REFERENCES JOBS(id_job)
);