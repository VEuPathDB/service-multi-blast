SELECT
  a.*
, b.user_id
, b.description
, b.max_download_size
FROM
  userlogins5.multiblast_jobs a
  INNER JOIN userlogins5.multiblast_users b
    ON a.job_digest = b.job_digest
WHERE
  a.job_digest = ?
  AND b.user_id = ?
