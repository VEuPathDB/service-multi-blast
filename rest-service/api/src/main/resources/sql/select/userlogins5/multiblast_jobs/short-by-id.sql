SELECT
  job_digest
, queue_id
, created_on
, delete_on
FROM
  userlogins5.multiblast_jobs
WHERE
  job_digest = ?
