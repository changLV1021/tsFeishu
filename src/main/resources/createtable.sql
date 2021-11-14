DROP TABLE TSYSPARAMETER;

create table TSYSPARAMETER (
  id INTEGER NOT NULL PRIMARY KEY generated always as identity (START WITH 1, INCREMENT BY 1) ,
  c_class         VARCHAR(20) not null,
  c_item          VARCHAR(40) not null,
  c_value         VARCHAR(255),
  c_describe      VARCHAR(60),
  c_valuebound    VARCHAR(60));

DROP TABLE TTASK;
create table TTASK (
  ID                   INTEGER NOT NULL PRIMARY KEY generated always as identity (START WITH 1, INCREMENT BY 1)               ,
  C_TASKNAME           VARCHAR(50)   not null       ,
  C_CORN               VARCHAR(20)    not null      ,
  C_STATUS             CHAR(1)              ,
  N_NUM                INTEGER
);

DROP TABLE TUSER;
create table TUSER (
  ID                   INTEGER NOT NULL PRIMARY KEY generated always as identity (START WITH 1, INCREMENT BY 1),
  C_USERNAME           VARCHAR(20)       not null   ,
  C_PASSWORD           VARCHAR(8)       not null
);

DROP TABLE TTASK_CONFIG;
create table TTASK_CONFIG (
  ID                   INTEGER NOT NULL PRIMARY KEY generated always as identity (START WITH 1, INCREMENT BY 1)                 ,
  C_TASKID             INTEGER          not null       ,
  C_DINGDING_URL       VARCHAR(255)    not null     ,
  C_LOGINFLAG          VARCHAR(20)      not null    ,
  C_LISTFLAG           VARCHAR(20)      not null    ,
  C_TSNAME             VARCHAR(40)     not null     ,
  C_TSPASS             VARCHAR(20)     not null     ,
  C_TEMPNAME           VARCHAR(200)    not null     ,
  C_TITLE              VARCHAR(200)     not null    ,
  C_ONLYOVERDUE        CHAR(1)              ,
  C_ONLYTODAY          CHAR(1)              ,
  C_ONLYEXPIRE         CHAR(1)              ,
  C_ISSHOWNUM          CHAR(1)              ,
  C_ISSHOWCUST         CHAR(1)              ,
  C_ISSHOWCONSTENT     CHAR(1)              ,
  C_ISSHOWHUNDLER      CHAR(1)              ,
  C_ISSHOWREVIEWER     CHAR(1)              ,
  C_ISSHOWTYPE         CHAR(1)
);
