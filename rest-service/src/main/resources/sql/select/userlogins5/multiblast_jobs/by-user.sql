SELECT
  j.*
, u.description
FROM
  userlogins5.multiblast_jobs j
  INNER JOIN userlogins5.multiblast_users u
    ON u.job_digest = j.job_digest
WHERE
  u.user_id = ?
