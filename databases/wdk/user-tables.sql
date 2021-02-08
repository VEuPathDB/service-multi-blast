CREATE TABLE userlogins5.multiblast_jobs (
  -- SHA256 hash of the job configuration
  job_digest RAW(32) PRIMARY KEY

  -- Raw job configuration as a JSON blob
, job_config CLOB NOT NULL

  -- ID of the job in the job queue.
, queue_id   NUMBER(7)

  -- Timestamp for the job creation.  Used to detect "new" jobs created from a
  -- different instance.
, created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL

  -- Timestamp for the job deletion date.  After this date the job record is
  -- subject to automatic pruning.
, delete_on TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE userlogins5.multiblast_users (
  -- SHA256 hash of the job configuration
  job_digest  RAW(32) REFERENCES userlogins5.multiblast_jobs (job_digest)
  -- User associated with the job.
  -- This user may not be the one who originally created the job, any user that
  -- posts a job with the same hash will be linked to the job.
, user_id     NUMBER(12)  REFERENCES userlogins5.users (user_id)
  -- User provided description of their job run.
, description VARCHAR2(1024)
  -- Max file size (in bytes) the requester is willing to parse.
  --
  -- This limit is applied to all reports requested by this user unless the user
  -- provides an override max-size header when requesting the results directly.
  --
  -- A value of 0 indicates no max size.
, max_download_size NUMBER(12) DEFAULT 0 NOT NULL
  -- Unique user-to-job link
, CONSTRAINT mb_uq_user_job UNIQUE (job_digest, user_id)
)
