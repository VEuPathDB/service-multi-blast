UPDATE
  job.jobs
SET
  queue_id = ?
, modified_on = now()
WHERE
  job_id = ?
;
