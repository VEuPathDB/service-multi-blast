SELECT
  config
, status
FROM
  multiblast.multiblast_fmt_jobs
WHERE
  job_digest = ?
  AND report_digest = ?
  AND user_id = ?
