CREATE TABLE multi_blast.job_config
(
  job_id    INT NOT NULL REFERENCES multi_blast.jobs(job_id)
--   , option_id
);
