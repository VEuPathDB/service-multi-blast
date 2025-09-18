SELECT
  *
FROM
  multiblast.multiblast_job_to_targets t
  INNER JOIN multiblast.multiblast_users u
    ON t.job_digest = u.job_digest
WHERE
  u.user_id = ?
