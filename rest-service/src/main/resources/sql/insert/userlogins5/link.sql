INSERT INTO
  multiblast.multiblast_job_to_jobs (
    job_digest,
    parent_digest,
    position
  )
VALUES
  (?, ?, (
    SELECT
      COALESCE(MAX(position), 0) + 1
    FROM
      multiblast.multiblast_job_to_jobs
    WHERE
      parent_digest = ?
    )
  )
