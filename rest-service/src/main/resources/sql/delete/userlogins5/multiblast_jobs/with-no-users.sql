DELETE FROM userlogins5.multiblast_jobs j
WHERE NOT EXISTS(
  SELECT 1
  FROM userlogins5.multiblast_users u
  WHERE j.job_digest = u.job_digest
)