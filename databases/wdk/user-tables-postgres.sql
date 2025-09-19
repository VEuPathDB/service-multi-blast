-- Version 0 ---------------------------------------------------------------------------------------
set role COMM_WDK_W; -- TODO: remove GRANTs to COMM_WDK_W

CREATE SCHEMA IF NOT EXISTS multiblast;
GRANT USAGE ON SCHEMA multiblast TO comm_wdk_w;

CREATE TABLE multiblast.multiblast_jobs
(
  job_digest UUID
    PRIMARY KEY,
  job_config TEXT
    NOT NULL,
  query      TEXT
    NOT NULL,
  queue_id   BIGINT,
  project_id VARCHAR(16)
    NOT NULL,
  status     VARCHAR(11),
  created_on TIMESTAMP WITH TIME ZONE
    NOT NULL,
  delete_on  TIMESTAMP WITH TIME ZONE
    NOT NULL
);

CREATE TABLE multiblast.multiblast_job_to_targets
(
  job_digest  UUID
    REFERENCES multiblast.multiblast_jobs (job_digest)
    NOT NULL,
  organism    VARCHAR(256),
  target_file VARCHAR(256)
);

CREATE TABLE multiblast.multiblast_job_to_jobs
(
  job_digest    UUID
    REFERENCES multiblast.multiblast_jobs (job_digest)
    NOT NULL,
  parent_digest UUID
    REFERENCES multiblast.multiblast_jobs (job_digest)
    NOT NULL,
  position      INTEGER
    NOT NULL,
  CONSTRAINT mb_uq_job_job
    UNIQUE (job_digest, parent_digest)
);

CREATE TABLE multiblast.multiblast_users
(
  job_digest        UUID
    REFERENCES multiblast.multiblast_jobs (job_digest)
    NOT NULL,
  user_id           BIGINT
    REFERENCES wdkuser.users (user_id)
    NOT NULL,
  description       VARCHAR(1024),
  max_download_size BIGINT
    DEFAULT 0
    NOT NULL,
  run_directly      BOOLEAN
    NOT NULL,
  CONSTRAINT mb_uq_user_job
    UNIQUE (job_digest, user_id)
);

CREATE INDEX mb_us_job_digest ON multiblast.multiblast_users (job_digest);

GRANT SELECT ON multiblast.multiblast_jobs TO comm_wdk_w;
GRANT SELECT ON multiblast.multiblast_users TO comm_wdk_w;
GRANT SELECT ON multiblast.multiblast_job_to_jobs TO comm_wdk_w;
GRANT SELECT ON multiblast.multiblast_job_to_targets TO comm_wdk_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON multiblast.multiblast_jobs TO comm_wdk_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON multiblast.multiblast_users TO comm_wdk_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON multiblast.multiblast_job_to_jobs TO comm_wdk_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON multiblast.multiblast_job_to_targets TO comm_wdk_w;

-- Version 1 ---------------------------------------------------------------------------------------

CREATE TABLE multiblast.multiblast_fmt_jobs
(
  report_digest UUID PRIMARY KEY                                            NOT NULL,
  job_digest    UUID REFERENCES multiblast.multiblast_jobs (job_digest)    NOT NULL,
  status        VARCHAR(11)                                                 NOT NULL,
  config        TEXT                                                        NOT NULL,
  queue_id      BIGINT                                                      NOT NULL,
  created_on    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP          NOT NULL
);

CREATE TABLE multiblast.multiblast_users_to_fmt_jobs
(
  report_digest UUID REFERENCES multiblast.multiblast_fmt_jobs (report_digest) NOT NULL,
  user_id       BIGINT                                                          NOT NULL,
  description   VARCHAR(1024)
);

GRANT SELECT ON multiblast.multiblast_fmt_jobs TO comm_wdk_w;
GRANT SELECT ON multiblast.multiblast_users_to_fmt_jobs TO comm_wdk_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON multiblast.multiblast_fmt_jobs TO comm_wdk_w;
GRANT SELECT, INSERT, UPDATE, DELETE ON multiblast.multiblast_users_to_fmt_jobs TO comm_wdk_w;
