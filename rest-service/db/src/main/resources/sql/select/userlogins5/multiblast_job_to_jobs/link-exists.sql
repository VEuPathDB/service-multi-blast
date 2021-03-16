SELECT
  1
FROM
  userlogins5.multiblast_job_to_jobs
WHERE
  parent_digest = ?
  AND job_digest = ?