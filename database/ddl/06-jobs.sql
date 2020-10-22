CREATE TABLE multi_blast.jobs
(
  job_id      SERIAL PRIMARY KEY
, user_id     BIGINT   NOT NULL
, status_id   SMALLINT NOT NULL REFERENCES multi_blast.job_status (status_id)
, created_on  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
, modified_on TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);
