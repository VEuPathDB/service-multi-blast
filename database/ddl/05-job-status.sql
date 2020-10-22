CREATE TABLE multi_blast.job_status
(
  status_id SMALLINT    NOT NULL PRIMARY KEY
  , name      VARCHAR(16) NOT NULL UNIQUE
);

INSERT INTO
  multi_blast.job_status (status_id, name)
VALUES
  (1, 'queued')
, (2, 'in-progress')
, (3, 'errored')
, (4, 'completed')
;
