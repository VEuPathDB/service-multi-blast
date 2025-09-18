SELECT
  *
FROM
  multiblast.multiblast_fmt_jobs
WHERE
  job_digest = ?
  AND user_id = ?
