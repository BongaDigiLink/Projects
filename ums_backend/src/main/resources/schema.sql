CREATE TABLE Interns
(
    intern_id int auto_increment,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL ,
    gender varchar(10) NOT NULL,
    fieldOfWork varchar(255) NOT NULL,
    cellphone_num int NOT NULL
);

CREATE TABLE Mentors
(
    mentor_id int auto_increment,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL ,
    gender varchar(10) NOT NULL,
    fieldOfWork varchar(255) NOT NULL,
    cellphone_num int NOT NULL
);

INSERT INTO Interns(name, surname, gender, fieldOfWork, cellphone_num)
VALUES ('Bonga','Gougota','male','Intern SoftwareDev.',0760659050)
     ,('Athiora','Gougota','female','Intern Analytics',0760234550)
     ,('Bonga','Gougota','male','Intern SoftwareDev.',0816659050);