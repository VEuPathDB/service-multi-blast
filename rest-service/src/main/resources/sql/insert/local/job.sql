INSERT INTO
  job.jobs (user_id, status_id, tool_id)
VALUES
  (?, ?, ?)
RETURNING
  job_id,
  created_on,
  modified_on
;
