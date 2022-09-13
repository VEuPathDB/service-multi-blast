CREATE SCHEMA AUTHORIZATION mblast;


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
);

CREATE TABLE mblast.query_to_targets (
  query_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_target_to_queries_fk REFERENCES mblast.query_configs (query_job_id)
, organism     VARCHAR2(512)
    NOT NULL
, target_file  VARCHAR2(512)
    NOT NULL
);


CREATE TABLE mblast.user_to_queries (
  query_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_user_to_queries_fk REFERENCES mblast.query_configs (query_job_id)
, user_id      NUMBER(12)
    NOT NULL
, summary      VARCHAR2(512)
, description  CLOB
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
);


CREATE TABLE mblast.user_to_reports (
  report_job_id CHAR(32)
    NOT NULL
    CONSTRAINT mblast_user_to_reports_fk REFERENCES mblast.report_configs (report_job_id)
, user_id       NUMBER(12)
    NOT NULL
, summary       VARCHAR2(512)
, description   CLOB
);