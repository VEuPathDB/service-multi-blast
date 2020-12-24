UPDATE
  job.jobs
SET
  status_id = ?
, modified_on = now()
WHERE
  job_id = ?
;
