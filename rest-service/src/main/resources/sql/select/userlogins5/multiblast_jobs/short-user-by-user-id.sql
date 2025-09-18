SELECT
  a.job_digest
, a.queue_id
, a.created_on
, a.delete_on
, a.project_id
, a.status
, b.user_id
, b.description
, b.max_download_size
, b.run_directly
FROM
  multiblast.multiblast_jobs a
  INNER JOIN multiblast.multiblast_users b
    ON a.job_digest = b.job_digest
WHERE
  b.user_id = ?
