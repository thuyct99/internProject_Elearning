INSERT INTO roles (roleid, rolename) VALUES (1, 'Admin');
INSERT INTO roles (roleid, rolename) VALUES (2, 'Teacher');
INSERT INTO roles (roleid, rolename) VALUES (3, 'Student');

INSERT INTO questiontypes (questiontypeid, questiontypecode, questiontypename) VALUES (1, 'MULTIPLE', 'Multiple question');
INSERT INTO questiontypes (questiontypeid, questiontypecode, questiontypename) VALUES (2, 'YESNO', 'Yes/No question');
INSERT INTO questiontypes (questiontypeid, questiontypecode, questiontypename) VALUES (3, 'INPUT', 'Input type');

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10000, 'admin', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'Administrator1', 'admin@codeenginestudio.com', true, 1);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10002, 'teacher', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'teacher', 'teacher@codeenginestudio.com', true, 2);

INSERT INTO users (userid, username, password, firstname, email, enabled, roleid) VALUES (10006, 'student', '$2a$11$tUilnpRbUmpBxKWap3oUP..fNsY19D6n/WrdcXc94YnF4yCgXVJty', 'student', 'student@codeenginestudio.com', true, 3);

INSERT INTO classes (classid, classname, status, teacherid) VALUES (100, 'Clazz 1', true, 10002);
INSERT INTO classes (classid, classname, status, teacherid) VALUES (200, 'Clazz 2', false, 10002);
INSERT INTO classes (classid, classname,  status, teacherid) VALUES (300, 'Clazz 3', true, 10003);

INSERT INTO class_students (idrow, classid, studentid) VALUES (100, 100, 10006);

INSERT INTO assessments (assessmentid, assessmentname, classid, startdate, expireddate, status) VALUES (100, 'toan hoc', '100', '2020/6/10', '2020/8/30', true);

INSERT INTO assessments (assessmentid, assessmentname, classid, startdate, expireddate, status) VALUES (101, 'van hoc', '100', '2020/6/10', '2020/8/30', false);

