SELECT
  *
FROM
  userlogins5.multiblast_fmt_jobs
WHERE
  job_digest = ?
  AND user_id = ?
