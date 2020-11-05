CREATE TABLE job.config
(
  job_id    INT      NOT NULL REFERENCES job.jobs (job_id)
, option_id SMALLINT NOT NULL REFERENCES blast.options (option_id)
, value     VARCHAR
);
