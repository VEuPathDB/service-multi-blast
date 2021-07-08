-- Version 0 ---------------------------------------------------------------------------------------

CREATE TABLE userlogins5.multiblast_jobs
(
  job_digest RAW(16)
    PRIMARY KEY,
  job_config CLOB
    NOT NULL,
  query      CLOB
    NOT NULL,
  queue_id   NUMBER(7),
  project_id VARCHAR2(16)
    NOT NULL,
  status     VARCHAR2(11),
  created_on TIMESTAMP WITH TIME ZONE
    NOT NULL,
  delete_on  TIMESTAMP WITH TIME ZONE
    NOT NULL
);

CREATE TABLE userlogins5.multiblast_job_to_targets
(
  job_digest  RAW(16)
    REFERENCES userlogins5.multiblast_jobs (job_digest)
    NOT NULL,
  organism    VARCHAR2(256),
  target_file VARCHAR2(256)
);

CREATE TABLE userlogins5.multiblast_job_to_jobs
(
  job_digest    RAW(16)
    REFERENCES userlogins5.multiblast_jobs (job_digest)
    NOT NULL,
  parent_digest RAW(16)
    REFERENCES userlogins5.multiblast_jobs (job_digest)
    NOT NULL,
  position      NUMBER(4)
    NOT NULL,
  CONSTRAINT mb_uq_job_job
    UNIQUE (job_digest, parent_digest)
);

CREATE TABLE userlogins5.multiblast_users
(
  job_digest        RAW(16)
    REFERENCES userlogins5.multiblast_jobs (job_digest)
    NOT NULL,
  user_id           NUMBER(12)
    REFERENCES userlogins5.users (user_id),
  description       VARCHAR2(1024),
  max_download_size NUMBER(12)
    DEFAULT 0
    NOT NULL,
  run_directly      NUMBER(1)
    NOT NULL,
  CONSTRAINT mb_uq_user_job
    UNIQUE (job_digest, user_id)
);

GRANT SELECT ON userlogins5.multiblast_jobs TO webwww;
GRANT SELECT ON userlogins5.multiblast_users TO webwww;
GRANT SELECT ON userlogins5.multiblast_job_to_jobs TO webwww;
GRANT SELECT ON userlogins5.multiblast_job_to_targets TO webwww;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_jobs TO webwww;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_users TO webwww;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_job_to_jobs TO webwww;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_job_to_targets TO webwww;

-- Version 1 ---------------------------------------------------------------------------------------

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
