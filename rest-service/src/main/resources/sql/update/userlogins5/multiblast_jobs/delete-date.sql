UPDATE
  multiblast.multiblast_jobs
SET
  delete_on = ?
WHERE
  job_digest = ?
