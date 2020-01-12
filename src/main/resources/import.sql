INSERT INTO entry_type(type) VALUES('DIRECTORY');
INSERT INTO entry_type(type) VALUES('FILE');

INSERT INTO dir(id,date, base_dir) VALUES(1,TIMESTAMP ( '2016-09-14', '18:03:00' ),'/opt/tomcat/temp');
INSERT INTO dir(id,date, base_dir) VALUES(2,TIMESTAMP ( '2016-09-14', '10:27:00' ),'/opt/tomcat/temp');

INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('innerTemp',2,1,null);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('X-FILES',2,1,null);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('f.txt',1,1,4383);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('F1.txt',1,1,12872);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('f4_99.JPG',1,2,1556480);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('f4_00127.pdf',1,2,923116);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('f0008.doc',1,2,26634240);
INSERT INTO dir_entry(name,entry_type_id,dir_id,size) VALUES('function.cpp',1,2,3656);