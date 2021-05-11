SELECT
  a.*
FROM
  userlogins5.multiblast_jobs a
  INNER JOIN userlogins5.multiblast_job_to_jobs b
    ON a.job_digest = b.job_digest
WHERE
  b.parent_digest = ?
