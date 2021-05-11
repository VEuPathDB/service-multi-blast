SELECT
  job_digest
FROM
  userlogins5.multiblast_jobs a
WHERE
  NOT EXISTS (
    SELECT 1
    FROM userlogins5.multiblast_users b
    WHERE a.job_digest = b.job_digest
  )