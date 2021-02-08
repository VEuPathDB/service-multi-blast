SELECT
  a.job_digest
, a.queue_id
, a.created_on
, a.delete_on
, b.user_id
, b.description
FROM
  userlogins5.multiblast_jobs a
  INNER JOIN userlogins5.multiblast_users b
    ON a.job_digest = b.job_digest
WHERE
  b.user_id = ?
