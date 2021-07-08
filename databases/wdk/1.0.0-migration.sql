ALTER TABLE userlogins5.multiblast_jobs MODIFY status VARCHAR2(11);

CREATE TABLE userlogins5.multiblast_fmt_jobs
(
  report_digest RAW(16) PRIMARY KEY                                         NOT NULL,
  job_digest    RAW(16) REFERENCES userlogins5.multiblast_jobs (job_digest) NOT NULL,
  status        VARCHAR2(11)                                                NOT NULL,
  config        CLOB                                                        NOT NULL,
  queue_id      NUMBER(7)                                                   NOT NULL,
  created_on    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP          NOT NULL
);

CREATE TABLE userlogins5.multiblast_users_to_fmt_jobs
(
  report_digest RAW(16) REFERENCES userlogins5.multiblast_fmt_jobs (report_digest) NOT NULL,
  user_id       NUMBER(12)                                                         NOT NULL,
  description   VARCHAR2(1024)
);

GRANT SELECT ON userlogins5.multiblast_fmt_jobs TO webwww;
GRANT SELECT ON userlogins5.multiblast_users_to_fmt_jobs TO webwww;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_fmt_jobs TO webwww;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_users_to_fmt_jobs TO webwww;
