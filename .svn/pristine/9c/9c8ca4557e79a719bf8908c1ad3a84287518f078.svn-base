create table  MST_PARAMETER
(
       Id                INTEGER primary key auto_increment not null,
       code              VARCHAR(255),
       name              VARCHAR(32),
       description       VARCHAR(255),
       param_type        VARCHAR(32),
       value             VARCHAR(32),
       script_id         INTEGER,
       script_content    text
);

create index IDX_MST_PARAMETER_code on MST_PARAMETER(code,param_type);