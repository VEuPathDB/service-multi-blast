DELETE FROM userlogins5.multiblast_users d
WHERE EXISTS (
  SELECT 1
  FROM
    userlogins5.multiblast_jobs j
  , userlogins5.users a
  WHERE
    d.job_digest = j.job_digest
    AND d.user_id = a.user_id
    AND j.delete_on <= current_timestamp
    AND a.is_guest = 1
)
