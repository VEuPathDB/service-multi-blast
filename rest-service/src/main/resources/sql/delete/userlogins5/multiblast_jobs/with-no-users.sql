DELETE FROM multiblast.multiblast_jobs j
WHERE NOT EXISTS(
  SELECT 1
  FROM multiblast.multiblast_users u
  WHERE j.job_digest = u.job_digest
)
