SELECT
  a.parent_digest
, a.position
FROM
  userlogins5.multiblast_job_to_jobs a
  INNER JOIN userlogins5.multiblast_users b
    ON a.job_digest = b.job_digest
WHERE
  b.user_id = ?
  AND a.job_digest = ?