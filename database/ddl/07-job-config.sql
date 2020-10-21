CREATE TABLE job_config
(
  job_id    INT NOT NULL REFERENCES jobs(job_id)
  , option_id
);
