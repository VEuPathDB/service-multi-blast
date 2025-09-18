SELECT
  1
FROM
  multiblast.multiblast_job_to_jobs
WHERE
  parent_digest = ?
  AND job_digest = ?
