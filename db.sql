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
	FOREIGN KEY(submitter) REFERENCES CITIZEN_USERS(username)
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
	FOREIGN KEY(related_company) REFERENCES COMPANY_USERS(username),
	FOREIGN KEY(submitter) REFERENCES CITIZEN_USERS(username)
);

CREATE TABLE `VOLUNTEERING_EVENTS`
(
    id_event INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_community_report INTEGER,
	creation_date DATE,
    event_date DATE,
    FOREIGN KEY (id_community_report) REFERENCES COMMUNITY_REPORTS(id_report)
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
    id_company_report INTEGER,
	start_date DATE,
	end_date DATE,
    state ENUM('A','R','E'), -- Accepted, Refused, Ended
    job_info BLOB,
    FOREIGN KEY (id_company_report) REFERENCES COMPANY_REPORTS(id_report)
);


insert into citizen_users values ('ShockGiammy', 'gian1998', 'Gian Marco', 'Falcone', null);
insert into citizen_users values ('fra_nali', 'fra1998', 'Francesco', 'Fanali', null);
insert into community_reports values (null, 'Immondizia ovunque!1!', 'Non si può vedere!', curdate(), 'Via Luigi Luzzatti', '41.8919300', '12.5113300', 'ShockGiammy',  null);
insert into community_reports values (null, 'Immondizia per la strada!!', 'Non si può camminare!', curdate(), 'Via Pietro Micca', '41.8929402', '12.5114345', 'fra_nali',  null);