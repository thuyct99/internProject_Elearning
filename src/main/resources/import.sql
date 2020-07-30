INSERT INTO roles (roleid, rolename) VALUES (1, 'Admin');
INSERT INTO roles (roleid, rolename) VALUES (2, 'Teacher');
INSERT INTO roles (roleid, rolename) VALUES (3, 'Student');

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10000, 'admin', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'Administrator1', 'admin@codeenginestudio.com', true, 1);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10001, 'teacher A', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'teacher1', 'teacher@codeenginestudio.com', true, 2);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10002, 'teacher B', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'student2', 'hau.bu22i@codeenginestudio.com', true, 3);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10003, 'teacher C', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'teacher1', 'teacher@codeenginestudio.com', true, 2);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10006, 'student', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'Administrator1', 'amy1217499@gmail.com', true, 3);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10007, 'thuycao', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty','Thuy', 'badmin11@codeenginestudio.com', true, 3);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10004, 'student C', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'Administrator1', 'cadmin11@codeenginestudio.com', true, 3);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10005, 'student D', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'Administrator1', 'dadmin11@codeenginestudio.com', true, 3);

INSERT INTO classes (classid, classname, status, teacherid) VALUES (100, 'Lop1 Lop1', true, 10003);
INSERT INTO classes (classid, classname, status, teacherid) VALUES (200, 'lop2 Lop1', false, 10003);
INSERT INTO classes (classid, classname,  status, teacherid) VALUES (300, 'lop3 Lop1', true, 10003);

INSERT INTO class_students (idrow, classid, studentid) VALUES (100, 100, 10006);
INSERT INTO class_students (idrow, classid, studentid) VALUES (200, 100, 10007);



INSERT INTO questiontypes (questiontypeid, questiontypename) VALUES (1, 'Multiple question');
INSERT INTO questiontypes (questiontypeid, questiontypename) VALUES (2, 'Yes/No question');

INSERT INTO assessments (assessmentid, assessmentname, classid, startdate, expireddate, status) VALUES (100, 'toan hoc', '100', '2020/6/10', '2020/7/20', true);

INSERT INTO assessments (assessmentid, assessmentname, classid, startdate, expireddate, status) VALUES (101, 'van hoc', '100', '2020/6/10', '2020/7/20', true);

INSERT INTO assessments (assessmentid, assessmentname, classid, startdate, expireddate, status) VALUES (102, 'My thuat', '100', '2020/6/10', '2020/7/20', true);

INSERT INTO assessments (assessmentid, assessmentname, classid, startdate, expireddate, status) VALUES (103, 'Tin hoc', '100', '2020/6/10', '2020/7/20', true);

INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (100, 1, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'A', 10, 2, 100);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (102, 2, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'B', 10, 2, 100);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (103, 3, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'C', 10, 2, 100);

INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (1000, 1, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'A', 10, 2, 101);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (2000, 2, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 101);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3000, 3, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 101);

INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3001, 1, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 103);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3002, 2, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 103);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3003, 3, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 103);

INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3004, 1, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 102);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3005, 2, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 102);
INSERT INTO questions (questionid, numericalorder, content, options, correctanswer, score, questiontypeid, assessmentid) VALUES (3006, 3, 'what is the sound of one hand clapping?', '[{"name":"A","optionValue":"1"},{"name":"B","optionValue":"d"},{"name":"C","optionValue":"d"},{"name":"D","optionValue":"d"}]', 'D', 10, 2, 102);


INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (301, 'D', 7 , '2020/6/10', '2020/7/15', 101, 100, 10006);
INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (302, 'A', 10 , '2020/6/10', '2020/7/15', 101, 102, 10006);
INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (303, 'D', 7 , '2020/6/10', '2020/7/20', 101, 103, 10006);

INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (304, 'D', 7 , '2020/6/10', '2020/7/15', 100, 1000, 10006);
INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (305, 'B', 10 , '2020/6/10', '2020/7/15', 100, 2000, 10006);
INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (306, 'D', 7 , '2020/6/10', '2020/7/20', 100, 3000, 10006);

INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (307, 'D', 7 , '2020/6/10', '2020/7/15', 102, 3004, 10006);
INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (308, 'D', 10 , '2020/6/10', '2020/7/15', 102, 3005, 10006);
INSERT INTO results (id, answerchoice, score, startdate, updatedate, assessmentid, questionid, studentid) VALUES (309, 'C', 7 , '2020/6/10', '2020/7/20', 102, 3006, 10006);
