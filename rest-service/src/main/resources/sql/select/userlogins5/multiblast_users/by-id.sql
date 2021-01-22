SELECT
  user_id
, description
FROM
  userlogins5.multiblast_users
WHERE
  job_digest = ?
