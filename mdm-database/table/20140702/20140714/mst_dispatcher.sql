create table  mst_dispatcher
(
       id                INTEGER  primary key auto_increment not null,
       dispatch_address  VARCHAR(64) not null,
       name              VARCHAR(10) not null,
       sex               VARCHAR(1) not null,
       telephone         VARCHAR(11) not null,
       identification    VARCHAR(18) not null,
       sys_pwd           VARCHAR(20) not null,
       pwd_work_date     DATETIME not null,
       pwd_overdue_date  DATETIME not null,
       isActivity        VARCHAR(1) not null,
       create_people     VARCHAR(10) not null,
       insertDate        DATETIME not null,
       modify_people     VARCHAR(10) not null,
       modifiedDate      DATETIME not null,
       COMMENT           VARCHAR(256)
);
