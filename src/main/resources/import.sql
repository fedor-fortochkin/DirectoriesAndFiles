INSERT INTO dir(id,date, base_dir) VALUES(1,TIMESTAMP ( '2016-09-14', '18:03:00' ),'/opt/tomcat/temp');
INSERT INTO dir(id,date, base_dir) VALUES(2,TIMESTAMP ( '2016-09-14', '10:27:00' ),'/opt/tomcat/temp');

INSERT INTO dir_entry(name,type,dir_id,size) VALUES('innerTemp','DIRECTORY',1,null);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('X-FILES','DIRECTORY',1,null);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('f.txt','FILE',1,4383);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('F1.txt','FILE',1,12872);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('f4_99.JPG','FILE',2,1556480);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('f4_00127.pdf','FILE',2,923116);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('f0008.doc','FILE',2,26634240);
INSERT INTO dir_entry(name,type,dir_id,size) VALUES('function.cpp','FILE',2,3656);


-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('innerTemp',2,null);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('X-FILES',2,null);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('f.txt',1,4383);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('F1.txt',1,12872);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('f4_99.JPG',1,1556480);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('f4_00127.pdf',1,923116);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('f0008.doc',1,26634240);
-- INSERT INTO dir_entry(name,entry_type_id,size) VALUES('function.cpp',1,3656);