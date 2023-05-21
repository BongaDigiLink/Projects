
CREATE TABLE INTERNS
(
    ID            int AUTO_INCREMENT,
    email         VARCHAR(25) NOT NULL,
    name          VARCHAR(15),
    PASSWORD      VARCHAR(16),
    ROLE          VARCHAR(10),
    ACTIVE_STATUS VARCHAR(10),
    TRAINING_FIELD VARCHAR(255) NOT NULL,
    SURNAME       VARCHAR(25)
);

CREATE TABLE Skills
(
    ID int AUTO_INCREMENT,
    NAME VARCHAR(30),
    DESCRIPTION   VARCHAR(255),
    PROGRAMME_DURATION VARCHAR(15),
    DUE_DATE      VARCHAR(16),
    FIELD_TRAINING VARCHAR(255)
);

INSERT INTO INTERNS(ACTIVE_STATUS, EMAIL, NAME, PASSWORD,ROLE,SURNAME, TRAINING_FIELD)
VALUES (TRUE,'bonga@gmail.com','Bonga','bonga@gmail.com','Mentor','Gougota', 'Software Development'),
(TRUE,'b.gee@mail.com','Bonga','b.gee@mail.com','Intern','Gougota', 'Software Development');

INSERT INTO SKILLS(NAME, DESCRIPTION, FIELD_TRAINING)
VALUES ('Softskills for Programmers','Please visit https://example.com/','Software Tester');