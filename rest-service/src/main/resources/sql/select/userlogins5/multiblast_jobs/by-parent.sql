SELECT
  a.*
FROM
  multiblast.multiblast_jobs a
  INNER JOIN multiblast.multiblast_job_to_jobs b
    ON a.job_digest = b.job_digest
WHERE
  b.parent_digest = ?
