/*==============================================================*/
/* Database name:  XWAY                                         */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/3/5 12:39:32                            */
/*==============================================================*/


DROP DATABASE IF EXISTS XWAY;

/*==============================================================*/
/* Database: XWAY                                               */
/*==============================================================*/
CREATE DATABASE XWAY DEFAULT CHARSET utf8 collate utf8_general_ci;

ALTER DATABASE XWAY DEFAULT CHARACTER SET UTF8;

DROP USER 'xway'@'%' IF EXISTS xway;
CREATE USER 'xway'@'%' IDENTIFIED BY 'mysql';
GRANT SELECT,INSERT,UPDATE,DELETE ON XWAY.* TO 'xway'@'%';

FLUSH PRIVILEGES;



USE XWAY;

/*==============================================================*/
/* Table: XT_ATTACHMENT                                         */
/*==============================================================*/
CREATE TABLE XT_ATTACHMENT
(
   ATTACHMENTID         INT NOT NULL AUTO_INCREMENT,
   FILENAME             VARCHAR(64) NOT NULL,
   EXTNAME              VARCHAR(4) NOT NULL,
   SIZE                 BIGINT NOT NULL,
   REALPATH             VARCHAR(64) NOT NULL,
   UPLOADDATE           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   OWNERID              INT NOT NULL,
   PRIMARY KEY (ATTACHMENTID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_BOOKMARK                                           */
/*==============================================================*/
CREATE TABLE XT_BOOKMARK
(
   BOOKMARKID           INT NOT NULL AUTO_INCREMENT,
   USERID               INT,
   NAME                 VARCHAR(32),
   LINK                 VARCHAR(128),
   PRIMARY KEY (BOOKMARKID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_CITY                                               */
/*==============================================================*/
CREATE TABLE XT_CITY
(
   CITYID               INT NOT NULL AUTO_INCREMENT,
   STATEID              INT,
   NAME                 VARCHAR(64) NOT NULL,
   CODE                 VARCHAR(5) NOT NULL,
   CAPITAL              INT DEFAULT 0,
   PRIMARY KEY (CITYID)
);

/*==============================================================*/
/* Table: XT_CONTACTS                                           */
/*==============================================================*/
CREATE TABLE XT_CONTACTS
(
   CONTACTSID           INT NOT NULL AUTO_INCREMENT,
   EMPLOYEEID           INT NOT NULL,
   NAME                 VARCHAR(64) NOT NULL,
   GENDER               VARCHAR(8),
   TEL1                 VARCHAR(32),
   TEL2                 VARCHAR(32),
   TEL3                 VARCHAR(32),
   MOBILE1              VARCHAR(32),
   MOBILE2              VARCHAR(32),
   MOBILE3              VARCHAR(32),
   EMAIL                VARCHAR(32),
   COMMENTS             VARCHAR(128),
   PRIMARY KEY (CONTACTSID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_CONTINENT                                          */
/*==============================================================*/
CREATE TABLE XT_CONTINENT
(
   CONTINENTID          INT NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(16) NOT NULL,
   CODE                 CHAR(2) NOT NULL,
   PRIMARY KEY (CONTINENTID)
);

/*==============================================================*/
/* Index: IDX_CONTINENT_CODE                                    */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_CONTINENT_CODE ON XT_CONTINENT
(
   CODE
);

/*==============================================================*/
/* Table: XT_COUNTRY                                            */
/*==============================================================*/
CREATE TABLE XT_COUNTRY
(
   COUNTRYID            INT NOT NULL AUTO_INCREMENT,
   CONTINENTID          INT,
   NAME                 NATIONAL VARCHAR(64) NOT NULL,
   ISO_CODE_2           NATIONAL CHAR(2) NOT NULL,
   ISO_CODE_3           NATIONAL CHAR(3) NOT NULL,
   PHONE_CODE           CHAR(3) NOT NULL,
   CURRENCY             CHAR(3) NOT NULL,
   PRIMARY KEY (COUNTRYID)
);

/*==============================================================*/
/* Index: UN_COUNTRY_NAME                                       */
/*==============================================================*/
CREATE UNIQUE INDEX UN_COUNTRY_NAME ON XT_COUNTRY
(
   NAME
);

/*==============================================================*/
/* Index: UN_COUNTRY_SHORTNAME                                  */
/*==============================================================*/
CREATE UNIQUE INDEX UN_COUNTRY_SHORTNAME ON XT_COUNTRY
(
   ISO_CODE_2
);

/*==============================================================*/
/* Index: UN_COUNTRY_CODE                                       */
/*==============================================================*/
CREATE UNIQUE INDEX UN_COUNTRY_CODE ON XT_COUNTRY
(
   ISO_CODE_3
);

/*==============================================================*/
/* Table: XT_CUSTOMER                                           */
/*==============================================================*/
CREATE TABLE XT_CUSTOMER
(
   USERID               INT NOT NULL,
   PRIMARY KEY (USERID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_CUSTOMIZE                                          */
/*==============================================================*/
CREATE TABLE XT_CUSTOMIZE
(
   CUSTOMIZEID          INT NOT NULL AUTO_INCREMENT,
   DATEFORMAT           VARCHAR(16) NOT NULL,
   TIMEFORMAT           VARCHAR(16) NOT NULL,
   DATETIMEFORMAT       VARCHAR(32) NOT NULL,
   PAGESIZE             INT NOT NULL,
   STYLE                VARCHAR(16) NOT NULL,
   PRIMARY KEY (CUSTOMIZEID)
);

/*==============================================================*/
/* Table: XT_DEPARTMENT                                         */
/*==============================================================*/
CREATE TABLE XT_DEPARTMENT
(
   DEPARTMENTID         INT NOT NULL AUTO_INCREMENT,
   ORGID                INT NOT NULL,
   NAME                 VARCHAR(32) NOT NULL,
   DESCRIPTION          VARCHAR(64),
   PARENTID             INT,
   TEL                  VARCHAR(16),
   BDELETE              INT DEFAULT 0,
   PRIMARY KEY (DEPARTMENTID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_EMPLOYEE                                           */
/*==============================================================*/
CREATE TABLE XT_EMPLOYEE
(
   USERID               INT NOT NULL,
   DEPARTMENTID         INT NOT NULL,
   PRIMARY KEY (USERID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Index: UN_EMPLOYEE_USERID                                    */
/*==============================================================*/
CREATE INDEX UN_EMPLOYEE_USERID ON XT_EMPLOYEE
(
   USERID
);

/*==============================================================*/
/* Table: XT_FIELD                                              */
/*==============================================================*/
CREATE TABLE XT_FIELD
(
   FIELDID              INT NOT NULL AUTO_INCREMENT,
   VIEWID               INT NOT NULL,
   NAME                 NATIONAL VARCHAR(32) NOT NULL,
   `FOREIGN`            VARCHAR(32),
   `LABEL`              VARCHAR(32),
   OGNL                 VARCHAR(64),
   TYPE                 NATIONAL VARCHAR(16) NOT NULL COMMENT 'pk, name,  char, int, date, datetime, enum',
   SIZE                 INT UNSIGNED,
   NULLABLE             INT DEFAULT 0,
   FORMAT               NATIONAL VARCHAR(32),
   ENUM                 VARCHAR(16),
   DISPLAYINDEX         INT NOT NULL DEFAULT 0,
   WIDTH                VARCHAR(8),
   SORTINDEX            INT DEFAULT 0,
   PRIMARY KEY (FIELDID)
);

/*==============================================================*/
/* Index: IDX_FIELD_NAME                                        */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_FIELD_NAME ON XT_FIELD
(
   VIEWID,
   NAME
);

/*==============================================================*/
/* Table: XT_LOCALE                                             */
/*==============================================================*/
CREATE TABLE XT_LOCALE
(
   LOCALEID             INT(10) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(64) NOT NULL,
   LANGUAGE             CHAR(2) NOT NULL,
   COUNTRY              CHAR(2),
   PRIMARY KEY (LOCALEID)
);

/*==============================================================*/
/* Table: XT_LOGIN                                              */
/*==============================================================*/
CREATE TABLE XT_LOGIN
(
   LOGINID              INT NOT NULL,
   LOGINNAME            VARCHAR(16) NOT NULL,
   PASSWORD             VARCHAR(64),
   COOKIE               CHAR(16) NOT NULL,
   `LOCK`               INT NOT NULL DEFAULT 0,
   PRIMARY KEY (LOGINID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Index: UN_LOGIN_LOGINNAME                                    */
/*==============================================================*/
CREATE UNIQUE INDEX UN_LOGIN_LOGINNAME ON XT_LOGIN
(
   LOGINNAME
);

/*==============================================================*/
/* Index: UN_LOGIN_COOKIE                                       */
/*==============================================================*/
CREATE UNIQUE INDEX UN_LOGIN_COOKIE ON XT_LOGIN
(
   COOKIE
);

/*==============================================================*/
/* Table: XT_LOGINSTATUS                                        */
/*==============================================================*/
CREATE TABLE XT_LOGINSTATUS
(
   LOGINSTATUSID        INT NOT NULL,
   LASTLOGINTIME        BIGINT DEFAULT 0,
   LASTIP               INT DEFAULT 0,
   LASTSUCCESS          INT,
   ENABLELOGINTIME      BIGINT,
   LOGINFAILTIMES       INT DEFAULT 0,
   PRIMARY KEY (LOGINSTATUSID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_MENU                                               */
/*==============================================================*/
CREATE TABLE XT_MENU
(
   MENUID               INT NOT NULL AUTO_INCREMENT,
   `LABEL`              VARCHAR(32) NOT NULL,
   `ORDER`              INT,
   PARENTID             INT,
   RESOURCEID           INT,
   PARAMETER            VARCHAR(64),
   PRIMARY KEY (MENUID)
)
ENGINE = INNODB;

ALTER TABLE XT_MENU AUTO_INCREMENT=10000;

/*==============================================================*/
/* Table: XT_NOTES                                              */
/*==============================================================*/
CREATE TABLE XT_NOTES
(
   NOTESID              INT NOT NULL AUTO_INCREMENT,
   USERID               INT,
   TITLE                VARCHAR(64),
   CONTENT              TEXT,
   CREATETIME           BIGINT,
   PRIMARY KEY (NOTESID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_ORG                                                */
/*==============================================================*/
CREATE TABLE XT_ORG
(
   ORGID                INT NOT NULL AUTO_INCREMENT,
   NAME                 NATIONAL VARCHAR(64),
   ADDRESS              VARCHAR(64),
   TEL                  VARCHAR(16),
   DESCRIPTION          VARCHAR(64),
   BDELETE              INT DEFAULT 0,
   PRIMARY KEY (ORGID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_PRIVILEGE                                          */
/*==============================================================*/
CREATE TABLE XT_PRIVILEGE
(
   PRIVILEGEID          INT NOT NULL AUTO_INCREMENT,
   NAME                 NATIONAL VARCHAR(64) NOT NULL,
   DESCRIPTION          NATIONAL VARCHAR(64),
   PRIMARY KEY (PRIVILEGEID)
);

/*==============================================================*/
/* Index: UN_PRIVILEGE_NAME                                     */
/*==============================================================*/
CREATE UNIQUE INDEX UN_PRIVILEGE_NAME ON XT_PRIVILEGE
(
   NAME
);

/*==============================================================*/
/* Table: XT_RESOURCE                                           */
/*==============================================================*/
CREATE TABLE XT_RESOURCE
(
   RESOURCEID           INT NOT NULL AUTO_INCREMENT,
   LINK                 NATIONAL VARCHAR(64) NOT NULL,
   `LABEL`              VARCHAR(64),
   DESCRIPTION          NATIONAL VARCHAR(64),
   CANMENU              INT DEFAULT 0 COMMENT '0 - no
            1 - yes',
   PRIVILEGEID          INT,
   STAY                 INT NOT NULL DEFAULT 0,
   PRIMARY KEY (RESOURCEID)
);

/*==============================================================*/
/* Index: UN_RESOURCE_LINK                                      */
/*==============================================================*/
CREATE UNIQUE INDEX UN_RESOURCE_LINK ON XT_RESOURCE
(
   LINK
);

/*==============================================================*/
/* Table: XT_ROLE                                               */
/*==============================================================*/
CREATE TABLE XT_ROLE
(
   ROLEID               INT(10) NOT NULL AUTO_INCREMENT,
   NAME                 NATIONAL VARCHAR(16) NOT NULL,
   DESCRIPTION          NATIONAL VARCHAR(32),
   PRIMARY KEY (ROLEID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Index: UN_ROLE_NAME                                          */
/*==============================================================*/
CREATE UNIQUE INDEX UN_ROLE_NAME ON XT_ROLE
(
   NAME
);

/*==============================================================*/
/* Table: XT_ROLEMENU                                           */
/*==============================================================*/
CREATE TABLE XT_ROLEMENU
(
   ROLEMENUID           INT NOT NULL AUTO_INCREMENT,
   ROLEID               INT(10),
   MENUID               INT,
   PRIMARY KEY (ROLEMENUID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_ROLEPRIVILEGE                                      */
/*==============================================================*/
CREATE TABLE XT_ROLEPRIVILEGE
(
   ROLEPRIVILEGEID      INT NOT NULL AUTO_INCREMENT,
   ROLEID               INT NOT NULL,
   PRIVILEGEID          INT NOT NULL,
   PRIMARY KEY (ROLEPRIVILEGEID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_STATE                                              */
/*==============================================================*/
CREATE TABLE XT_STATE
(
   STATEID              INT NOT NULL AUTO_INCREMENT,
   COUNTRYID            INT NOT NULL,
   NAME                 VARCHAR(64) NOT NULL,
   SHORTNAME            CHAR(2),
   CODE                 VARCHAR(4),
   SYMBOL               CHAR(2),
   PRIMARY KEY (STATEID)
);


/*==============================================================*/
/* Table: XT_TIMEZONE                                           */
/*==============================================================*/
CREATE TABLE XT_TIMEZONE
(
   TIMEZONEID           INT NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(64) NOT NULL,
   GMT                  VARCHAR(9) NOT NULL,
   PRIMARY KEY (TIMEZONEID)
);

/*==============================================================*/
/* Table: XT_TOOLBOX                                            */
/*==============================================================*/
CREATE TABLE XT_TOOLBOX
(
   TOOLBOXID            INT NOT NULL AUTO_INCREMENT,
   ICON                 VARCHAR(16) NOT NULL,
   NAME                 VARCHAR(16) NOT NULL,
   DESCRIPTION          VARCHAR(16),
   RESOURCEID           INT,
   FIX                  INT DEFAULT 0,
   `ORDER`              INT NOT NULL,
   PRIMARY KEY (TOOLBOXID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_USER                                               */
/*==============================================================*/
CREATE TABLE XT_USER
(
   USERID               INT NOT NULL AUTO_INCREMENT,
   TYPE                 CHAR(1) NOT NULL,
   NAME                 NATIONAL VARCHAR(16) NOT NULL,
   NICKNAME             NATIONAL VARCHAR(16),
   GENDER               VARCHAR(8),
   ROLEID               INT,
   BIRTHDAY             DATETIME,
   MOBILE               VARCHAR(16),
   TEL                  NATIONAL VARCHAR(16),
   EMAIL                NATIONAL VARCHAR(32),
   ADDRESS              NATIONAL VARCHAR(64),
   ZIPCODE              NATIONAL VARCHAR(16),
   HOMEPAGE             VARCHAR(64),
   PHOTO                VARCHAR(64),
   COUNTRYID            INT,
   LOCALEID             INT,
   TIMEZONEID           INT,
   CUSTOMIZEID          INT NOT NULL,
   ABOUTME              VARCHAR(128),
   BDELETE              INT DEFAULT 0,
   PRIMARY KEY (USERID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Index: IDX_USER_CUSTOMIZE                                    */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_USER_CUSTOMIZE ON XT_USER
(
   CUSTOMIZEID
);

/*==============================================================*/
/* Index: UN_USER_NAME                                          */
/*==============================================================*/
CREATE UNIQUE INDEX UN_USER_NAME ON XT_USER
(
   NAME,
   BDELETE
);

/*==============================================================*/
/* Table: XT_USEREXCLUDEFIELD                                   */
/*==============================================================*/
CREATE TABLE XT_USEREXCLUDEFIELD
(
   USEREXCLUDEFIELDID   INT NOT NULL AUTO_INCREMENT,
   USERID               INT NOT NULL,
   VIEWID               INT NOT NULL,
   FIELDID              INT NOT NULL,
   PRIMARY KEY (USEREXCLUDEFIELDID)
)
ENGINE = INNODB;

ALTER TABLE XT_USEREXCLUDEFIELD COMMENT 'add not display field, if null is all is display';

/*==============================================================*/
/* Table: XT_USERTOOLBOX                                        */
/*==============================================================*/
CREATE TABLE XT_USERTOOLBOX
(
   USERTOOLBOXID        INT NOT NULL AUTO_INCREMENT,
   USERID               INT,
   TOOLBOXID            INT,
   PRIMARY KEY (USERTOOLBOXID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_VACATION                                           */
/*==============================================================*/
CREATE TABLE XT_VACATION
(
   VACATIONID           INT NOT NULL AUTO_INCREMENT,
   TYPE                 VARCHAR(8) NOT NULL,
   STARTDATE            BIGINT NOT NULL,
   ENDDATE              BIGINT NOT NULL,
   REASON               VARCHAR(128),
   MEMO                 VARCHAR(128),
   USERID               INT NOT NULL,
   REQUESTTIME          BIGINT NOT NULL,
   REPLY                VARCHAR(32),
   XT__USERID           INT,
   REPLYTIME            BIGINT,
   PRIMARY KEY (VACATIONID)
)
ENGINE = INNODB;

/*==============================================================*/
/* Table: XT_VIEW                                               */
/*==============================================================*/
CREATE TABLE XT_VIEW
(
   VIEWID               INT NOT NULL AUTO_INCREMENT,
   NAME                 NATIONAL VARCHAR(32) NOT NULL,
   `LABEL`              VARCHAR(32),
   TYPE                 VARCHAR(6) NOT NULL COMMENT 'table
            enum
            view
            object',
   CLASSNAME            VARCHAR(64),
   SERVICENAME          VARCHAR(32),
   SERVICEINTERFACE     VARCHAR(64),
   SERVICEIMPL          VARCHAR(64),
   MODIFIEDTIME         TIMESTAMP NOT NULL,
   PRIMARY KEY (VIEWID)
);

/*==============================================================*/
/* Index: IDX_VIEW_NAME                                         */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_VIEW_NAME ON XT_VIEW
(
   NAME
);

ALTER TABLE XT_ATTACHMENT ADD CONSTRAINT FK_USER_ATTACHMENT FOREIGN KEY (OWNERID)
      REFERENCES XT_USER (USERID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_BOOKMARK ADD CONSTRAINT FK_USER_BOOKMARK FOREIGN KEY (USERID)
      REFERENCES XT_USER (USERID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_CITY ADD CONSTRAINT FK_STATE_CITY FOREIGN KEY (STATEID)
      REFERENCES XT_STATE (STATEID);

ALTER TABLE XT_CONTACTS ADD CONSTRAINT FK_EMPLOYEE_CONTACTS FOREIGN KEY (EMPLOYEEID)
      REFERENCES XT_EMPLOYEE (USERID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_COUNTRY ADD CONSTRAINT FK_CONTINENT_COUNTRY FOREIGN KEY (CONTINENTID)
      REFERENCES XT_CONTINENT (CONTINENTID) ON DELETE SET NULL;

ALTER TABLE XT_CUSTOMER ADD CONSTRAINT FK_USER_CUSTOMER FOREIGN KEY (USERID)
      REFERENCES XT_USER (USERID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_DEPARTMENT ADD CONSTRAINT FK_DEPARTMENT_DEPARTMENT FOREIGN KEY (PARENTID)
      REFERENCES XT_DEPARTMENT (DEPARTMENTID) ON DELETE CASCADE;

ALTER TABLE XT_DEPARTMENT ADD CONSTRAINT FK_ORG_DEPARTMENT FOREIGN KEY (ORGID)
      REFERENCES XT_ORG (ORGID) ON DELETE CASCADE;

ALTER TABLE XT_EMPLOYEE ADD CONSTRAINT FK_DEPARTMENT_EMPLOYEE FOREIGN KEY (DEPARTMENTID)
      REFERENCES XT_DEPARTMENT (DEPARTMENTID) ON DELETE CASCADE;

ALTER TABLE XT_EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_USER FOREIGN KEY (USERID)
      REFERENCES XT_USER (USERID) ON DELETE CASCADE;

ALTER TABLE XT_FIELD ADD CONSTRAINT FK_VIEW_FIELD FOREIGN KEY (VIEWID)
      REFERENCES XT_VIEW (VIEWID) ON DELETE CASCADE;

ALTER TABLE XT_LOGIN ADD CONSTRAINT FK_LOGIN_USER FOREIGN KEY (LOGINID)
      REFERENCES XT_USER (USERID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_LOGINSTATUS ADD CONSTRAINT FK_LOGIN_LOGINLOG FOREIGN KEY (LOGINSTATUSID)
      REFERENCES XT_LOGIN (LOGINID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_MENU ADD CONSTRAINT FK_MENU_MENU FOREIGN KEY (PARENTID)
      REFERENCES XT_MENU (MENUID) ON DELETE CASCADE;

ALTER TABLE XT_MENU ADD CONSTRAINT FK_RESOURCE_MENU FOREIGN KEY (RESOURCEID)
      REFERENCES XT_RESOURCE (RESOURCEID) ON DELETE SET NULL ON UPDATE RESTRICT;

ALTER TABLE XT_NOTES ADD CONSTRAINT FK_USER_NOTES FOREIGN KEY (USERID)
      REFERENCES XT_USER (USERID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_RESOURCE ADD CONSTRAINT FK_RESOURCE_PRIVILEGE FOREIGN KEY (PRIVILEGEID)
      REFERENCES XT_PRIVILEGE (PRIVILEGEID) ON DELETE RESTRICT;

ALTER TABLE XT_ROLEMENU ADD CONSTRAINT FK_MENU_ROLEMENU FOREIGN KEY (MENUID)
      REFERENCES XT_MENU (MENUID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_ROLEMENU ADD CONSTRAINT FK_ROLE_ROLEMENU FOREIGN KEY (ROLEID)
      REFERENCES XT_ROLE (ROLEID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_ROLEPRIVILEGE ADD CONSTRAINT FK_PRIVILEGE_ROLEPRIVILEGE FOREIGN KEY (PRIVILEGEID)
      REFERENCES XT_PRIVILEGE (PRIVILEGEID) ON DELETE CASCADE;

ALTER TABLE XT_ROLEPRIVILEGE ADD CONSTRAINT FK_ROLE_ROLEPRIVILEGE FOREIGN KEY (ROLEID)
      REFERENCES XT_ROLE (ROLEID) ON DELETE CASCADE;

ALTER TABLE XT_STATE ADD CONSTRAINT FK_COUNTRY_STATE FOREIGN KEY (COUNTRYID)
      REFERENCES XT_COUNTRY (COUNTRYID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_TOOLBOX ADD CONSTRAINT FK_RESOURCE_TOOLBOX FOREIGN KEY (RESOURCEID)
      REFERENCES XT_RESOURCE (RESOURCEID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_USER ADD CONSTRAINT FK_COUNTRY_USER FOREIGN KEY (COUNTRYID)
      REFERENCES XT_COUNTRY (COUNTRYID) ON DELETE SET NULL;

ALTER TABLE XT_USER ADD CONSTRAINT FK_CUSTOMIZE_USER FOREIGN KEY (CUSTOMIZEID)
      REFERENCES XT_CUSTOMIZE (CUSTOMIZEID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_USER ADD CONSTRAINT FK_LOCALE_USER FOREIGN KEY (LOCALEID)
      REFERENCES XT_LOCALE (LOCALEID) ON DELETE SET NULL;

ALTER TABLE XT_USER ADD CONSTRAINT FK_TIMEZONE_USER FOREIGN KEY (TIMEZONEID)
      REFERENCES XT_TIMEZONE (TIMEZONEID) ON DELETE SET NULL;

ALTER TABLE XT_USER ADD CONSTRAINT FK_USER_ROLE FOREIGN KEY (ROLEID)
      REFERENCES XT_ROLE (ROLEID);

ALTER TABLE XT_USEREXCLUDEFIELD ADD CONSTRAINT FK_FIELD_USEREXCLUDEFIELD FOREIGN KEY (FIELDID)
      REFERENCES XT_FIELD (FIELDID) ON DELETE CASCADE;

ALTER TABLE XT_USEREXCLUDEFIELD ADD CONSTRAINT FK_USER_USEREXCLUDEFIELD FOREIGN KEY (USERID)
      REFERENCES XT_USER (USERID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_USEREXCLUDEFIELD ADD CONSTRAINT FK_VIEW_USEREXCLUDEFIELD FOREIGN KEY (VIEWID)
      REFERENCES XT_VIEW (VIEWID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_USERTOOLBOX ADD CONSTRAINT FK_TOOLBOX_EMPLOYEETOOLBOX FOREIGN KEY (TOOLBOXID)
      REFERENCES XT_TOOLBOX (TOOLBOXID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_USERTOOLBOX ADD CONSTRAINT FK_USER_EMPLOYEETOOLBOX FOREIGN KEY (USERID)
      REFERENCES XT_USER (USERID) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE XT_VACATION ADD CONSTRAINT FK_EMPLOYEE_VACATION_REPLYID FOREIGN KEY (XT__USERID)
      REFERENCES XT_EMPLOYEE (USERID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE XT_VACATION ADD CONSTRAINT FK_EMPLOYEE_VACATION_REQUESTID FOREIGN KEY (USERID)
      REFERENCES XT_EMPLOYEE (USERID) ON DELETE RESTRICT ON UPDATE RESTRICT;

