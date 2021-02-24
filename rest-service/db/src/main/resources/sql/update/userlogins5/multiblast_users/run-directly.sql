UPDATE
  userlogins5.multiblast_users
SET
  run_directly = ?
WHERE
  user_id = ?
  AND job_digest = ?
