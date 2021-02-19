CREATE TABLE userlogins5.multiblast_jobs (
  job_digest RAW(16) PRIMARY KEY
, job_config CLOB NOT NULL
, query CLOB NOT NULL
, queue_id   NUMBER(7)
, created_on TIMESTAMP WITH TIME ZONE NOT NULL
, delete_on TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE userlogins5.multiblast_job_to_jobs (
  job_digest    RAW(16) REFERENCES userlogins5.multiblast_jobs (job_digest) NOT NULL
, parent_digest RAW(16) REFERENCES userlogins5.multiblast_jobs (job_digest) NOT NULL
, position      NUMBER(4) NOT NULL
, CONSTRAINT mb_uq_job_job UNIQUE (job_digest, parent_digest)
);

CREATE TABLE userlogins5.multiblast_users (
  job_digest        RAW(16)
    REFERENCES userlogins5.multiblast_jobs (job_digest) NOT NULL
, user_id           NUMBER(12)
    REFERENCES userlogins5.users (user_id)
, description       VARCHAR2(1024)
, max_download_size NUMBER(12) DEFAULT 0 NOT NULL
, run_directly      NUMBER(1)            NOT NULL
, CONSTRAINT mb_uq_user_job UNIQUE (job_digest, user_id)
);

GRANT SELECT ON userlogins5.multiblast_jobs TO useraccts_r;
GRANT SELECT ON userlogins5.multiblast_users TO useraccts_r;
GRANT SELECT ON userlogins5.multiblast_job_to_jobs TO useraccts_r;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_jobs TO useraccts_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_users TO useraccts_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON userlogins5.multiblast_job_to_jobs TO useraccts_w;
