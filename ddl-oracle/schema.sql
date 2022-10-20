CREATE USER mblast NO AUTHENTICATION;

CREATE TABLE mblast.query_configs (
  query_job_id CHAR(32)
    NOT NULL
    PRIMARY KEY
, project_id   VARCHAR2(32)
    NOT NULL
, config       CLOB
    NOT NULL
, query        CLOB
    NOT NULL
, created_on   TIMESTAMP WITH TIME ZONE
    NOT NULL
);

CREATE TABLE mblast.query_to_subqueries (
  parent_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_query_to_subqueries_fk_1 REFERENCES mblast.query_configs (query_job_id)
, child_job_id  CHAR(32)
    NOT NULL
    CONSTRAINT mblast_query_to_subqueries_fk_2 REFERENCES mblast.query_configs (query_job_id)
, position      NUMBER(9)
    NOT NULL
, CONSTRAINT mblast_query_to_subqueries_uq UNIQUE (parent_job_id, child_job_id)
);

CREATE TABLE mblast.query_to_targets (
  query_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_target_to_queries_fk REFERENCES mblast.query_configs (query_job_id)
, target_name  VARCHAR2(512)
    NOT NULL
, target_file  VARCHAR2(512)
    NOT NULL
, CONSTRAINT mblast_query_to_targets_uq UNIQUE (query_job_id, target_name, target_file)
);


CREATE TABLE mblast.query_to_users (
  query_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_query_to_users_fk REFERENCES mblast.query_configs (query_job_id)
, user_id      NUMBER(12)
    NOT NULL
, summary      VARCHAR2(512)
, description  CLOB
, CONSTRAINT mblast_query_to_users_uq UNIQUE (query_job_id, user_id)
);


CREATE TABLE mblast.report_configs (
  report_job_id CHAR(32)
    NOT NULL
    PRIMARY KEY
, query_job_id  CHAR(32)
    NOT NULL
    CONSTRAINT mblast_report_to_queries_fk REFERENCES mblast.query_configs (query_job_id)
, config        CLOB
    NOT NULL
, created_on    TIMESTAMP WITH TIME ZONE
    NOT NULL
);


CREATE TABLE mblast.report_to_users (
  report_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_report_to_users_fk REFERENCES mblast.report_configs (report_job_id)
, user_id       NUMBER(12)
    NOT NULL
, summary       VARCHAR2(512)
, description   CLOB
, CONSTRAINT mblast_report_to_users_uq UNIQUE (report_job_id, user_id)
);