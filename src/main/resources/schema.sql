drop table if exists PERSONS;
drop table if exists PHONES;
drop table if exists USERS;

create table PERSONS (
  ID varchar(36) not null,
  NAME varchar(100) not null,
  CREATED_DATE date not null,
  LAST_UPDATE_DATE date not null,
  PRIMARY KEY (ID)
);

create table PHONES (
  ID varchar(36) not null,
  PERSON_ID varchar(36) not null,
  NUMBER varchar(9) not null,
  CITY_CODE varchar(6) not null,
  COUNTRY_CODE varchar(6) not null,
  IS_ACTIVE boolean not null,
  CREATED_DATE date not null,
  PRIMARY KEY (ID),
  FOREIGN KEY (PERSON_ID) REFERENCES PERSONS (ID)
);

create table USERS (
  ID varchar(36) not null,
  PERSON_ID varchar(36) not null,
  EMAIL varchar(255) not null,
  PASSWORD varchar(255) not null,
  TOKEN varchar(255) not null,
  IS_ACTIVE boolean not null,
  LAST_LOGIN_DATE date not null,
  CREATED_DATE date not null,
  LAST_UPDATE_DATE date not null,
  PRIMARY KEY (ID),
  FOREIGN KEY (PERSON_ID) REFERENCES PERSONS (ID)
);

ALTER TABLE USERS ADD CONSTRAINT PERSON_USER UNIQUE (PERSON_ID);