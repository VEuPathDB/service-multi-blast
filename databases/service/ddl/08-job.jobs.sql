CREATE TABLE job.jobs
(
  job_id      SERIAL PRIMARY KEY
, user_id     BIGINT   NOT NULL
, status_id   SMALLINT NOT NULL REFERENCES job.status (status_id)
, created_on  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
, modified_on TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);
