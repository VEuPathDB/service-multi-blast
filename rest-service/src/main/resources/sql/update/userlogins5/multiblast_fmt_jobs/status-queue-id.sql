UPDATE
 multiblast.multiblast_fmt_jobs
SET
  status = ?
, queue_id = ?
WHERE
  job_digest = ?
  AND report_digest = ?
  AND user_id = ?
