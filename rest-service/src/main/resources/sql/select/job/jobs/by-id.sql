SELECT
  job_id
, user_id
, status_id
, tool_id
, created_on
, modified_on
FROM
  job.jobs
WHERE
  job_id = ?
;
