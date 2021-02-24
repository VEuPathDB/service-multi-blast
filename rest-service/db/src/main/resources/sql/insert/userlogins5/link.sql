INSERT INTO
  userlogins5.multiblast_job_to_jobs (
    job_digest,
    parent_digest,
    position
  )
VALUES
  (?, ?, (
    SELECT
      COALESCE(MAX(position), 0) + 1
    FROM
      userlogins5.multiblast_job_to_jobs
    WHERE
      parent_digest = ?
    )
  )