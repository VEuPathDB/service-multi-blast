SELECT
  job_digest
FROM
  multiblast.multiblast_jobs a
WHERE
  NOT EXISTS (
    SELECT 1
    FROM multiblast.multiblast_users b
    WHERE a.job_digest = b.job_digest
  )
