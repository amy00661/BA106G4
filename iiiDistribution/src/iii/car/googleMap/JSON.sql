DROP TABLE JSON;
DROP SEQUENCE json_seq;

CREATE TABLE JSON (
    JSON_ID                        VARCHAR2(20)        NOT NULL,
    JSON_NAME                 CLOB        NOT NULL,
    CONSTRAINT JSON_ID_PK PRIMARY KEY (JSON_ID)
);

CREATE SEQUENCE json_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO JSON (JSON_ID, JSON_NAME) VALUES (JSON_seq.NEXTVAL , 'AAAA');


select count(*) from v$process;
select value from v$parameter where name = 'processes' ;
alter system set processes = 30000 scope = spfile;
shutdown immediate;
startup;