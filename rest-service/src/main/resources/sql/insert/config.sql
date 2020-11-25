WITH vals AS (
  SELECT
    ? AS job_id
  , val[1] AS key
  , val[2] AS value
  FROM
    (VALUES (unnest(?::VARCHAR[][]))) AS val
)
INSERT INTO
  job.config (job_id, option_id, value)
SELECT
  job_id
, key
, value
FROM
  vals
;
