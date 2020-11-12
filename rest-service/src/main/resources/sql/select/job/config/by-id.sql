SELECT
  option_id
, value
FROM
  job.config
WHERE
  job_id = ?
;
