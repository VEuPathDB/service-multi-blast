UPDATE
 multiblast.multiblast_fmt_jobs
SET
  status = ?
WHERE
  job_digest = ?
  AND report_digest = ?
  AND user_id = ?
