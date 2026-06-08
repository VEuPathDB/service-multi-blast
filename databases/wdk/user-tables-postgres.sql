-- Version 0 ---------------------------------------------------------------------------------------
RESET ROLE;

CREATE SCHEMA IF NOT EXISTS multiblast AUTHORIZATION userdb_owner;
GRANT USAGE ON SCHEMA multiblast TO multiblast_r;

CREATE TABLE multiblast.multiblast_jobs (
  job_digest BYTEA PRIMARY KEY,
  job_config TEXT                     NOT NULL,
  query      TEXT                     NOT NULL,
  queue_id   BIGINT,
  project_id VARCHAR(16)              NOT NULL,
  status     VARCHAR(11),
  created_on TIMESTAMP WITH TIME ZONE NOT NULL,
  delete_on  TIMESTAMP WITH TIME ZONE NOT NULL
);

ALTER TABLE multiblast.multiblast_jobs OWNER TO userdb_owner;
GRANT SELECT ON multiblast.multiblast_jobs TO multiblast_r;
GRANT INSERT, UPDATE, DELETE ON multiblast.multiblast_jobs TO multiblast_w;

--------------

CREATE TABLE multiblast.multiblast_job_to_targets (
  job_digest  BYTEA REFERENCES multiblast.multiblast_jobs(job_digest) NOT NULL,
  organism    VARCHAR(256),
  target_file VARCHAR(256),
  CONSTRAINT mb_job_target_pk PRIMARY KEY (job_digest, organism)
);

ALTER TABLE multiblast.multiblast_job_to_targets OWNER TO userdb_owner;
GRANT SELECT ON multiblast.multiblast_job_to_targets TO multiblast_r;
GRANT INSERT, UPDATE, DELETE ON multiblast.multiblast_job_to_targets TO multiblast_w;

--------------

CREATE TABLE multiblast.multiblast_job_to_jobs (
  job_digest    BYTEA REFERENCES multiblast.multiblast_jobs(job_digest) NOT NULL,
  parent_digest BYTEA REFERENCES multiblast.multiblast_jobs(job_digest) NOT NULL,
  position      INTEGER                                                 NOT NULL,
  CONSTRAINT mb_job_job_pk PRIMARY KEY (job_digest, parent_digest)
);

ALTER TABLE multiblast.multiblast_job_to_jobs OWNER TO userdb_owner;
GRANT SELECT ON multiblast.multiblast_job_to_jobs TO multiblast_r;
GRANT INSERT, UPDATE, DELETE ON multiblast.multiblast_job_to_jobs TO multiblast_w;

--------------

CREATE TABLE multiblast.multiblast_users (
  job_digest        BYTEA REFERENCES multiblast.multiblast_jobs(job_digest) NOT NULL,
  user_id           BIGINT REFERENCES wdkuser.users(user_id)                NOT NULL,
  description       VARCHAR(1024),
  max_download_size BIGINT DEFAULT 0                                        NOT NULL,
  run_directly      BOOLEAN                                                 NOT NULL,
  CONSTRAINT mb_us_pk PRIMARY KEY (job_digest, user_id)
);

CREATE INDEX mb_us_job_digest ON multiblast.multiblast_users(job_digest);

ALTER TABLE multiblast.multiblast_users OWNER TO userdb_owner;
GRANT SELECT ON multiblast.multiblast_users TO multiblast_r;
GRANT INSERT, UPDATE, DELETE ON multiblast.multiblast_users TO multiblast_w;



-- Version 1 ---------------------------------------------------------------------------------------

CREATE TABLE multiblast.multiblast_fmt_jobs (
  report_digest BYTEA PRIMARY KEY                                       NOT NULL,
  job_digest    BYTEA REFERENCES multiblast.multiblast_jobs(job_digest) NOT NULL,
  status        VARCHAR(11)                                             NOT NULL,
  config        TEXT                                                    NOT NULL,
  queue_id      BIGINT                                                  NOT NULL,
  created_on    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP      NOT NULL
);

ALTER TABLE multiblast.multiblast_fmt_jobs OWNER TO userdb_owner;
GRANT SELECT ON multiblast.multiblast_fmt_jobs TO multiblast_r;
GRANT INSERT, UPDATE, DELETE ON multiblast.multiblast_fmt_jobs TO multiblast_w;

--------------

CREATE TABLE multiblast.multiblast_users_to_fmt_jobs (
  report_digest BYTEA REFERENCES multiblast.multiblast_fmt_jobs(report_digest) NOT NULL,
  user_id       BIGINT                                                         NOT NULL,
  description   VARCHAR(1024),
  CONSTRAINT mutfmj_pk PRIMARY KEY (report_digest, user_id)
);

ALTER TABLE multiblast.multiblast_users_to_fmt_jobs OWNER TO userdb_owner;
GRANT SELECT ON multiblast.multiblast_users_to_fmt_jobs TO multiblast_r;
GRANT INSERT, UPDATE, DELETE ON multiblast.multiblast_users_to_fmt_jobs TO multiblast_w;
