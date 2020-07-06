INSERT INTO Announcement (ANNOUNCEMENT_NAME,SALARY,JOB,STATUS,ENGLISH) VALUES ('Convocatoria para salir de pobre',4000000,'JAVA','OPEN','YES');
INSERT INTO Announcement (ANNOUNCEMENT_NAME,SALARY,JOB,STATUS,ENGLISH) VALUES ('Comida marvel',3,'JAVA','CLOSED','NO');
INSERT INTO Candidate (CANDIDATE_NAME,PROGRAMMING_LANGUAGE,ENGLISH,SALARY,SOFT_SKILL) VALUES ('Weiner Eliecer','JAVA','NO','300000','NINGUNA');
INSERT INTO Candidate (CANDIDATE_NAME,PROGRAMMING_LANGUAGE,ENGLISH,SALARY,SOFT_SKILL) VALUES ('Jaime Mejia','JAVA','YES','300000','T0DAS');
INSERT INTO candidate_items (announcement_id, candidate_id) VALUES(1, 1);
INSERT INTO candidate_items (announcement_id, candidate_id) VALUES(2, 1);
INSERT INTO candidate_items (announcement_id, candidate_id) VALUES(2, 1);
INSERT INTO announcement_items (candidate_id, announcement_id) VALUES(2, 1);
