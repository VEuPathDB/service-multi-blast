CREATE TABLE IF NOT EXISTS blast.tasks (
  task_id SMALLINT NOT NULL PRIMARY KEY
, tool_id SMALLINT NOT NULL REFERENCES blast.tools(tool_id)
, name    VARCHAR(16) NOT NULL
, CONSTRAINT un_task_to_tool UNIQUE (tool_id, name)
);

INSERT INTO
  blast.tasks (task_id, tool_id, name)
VALUES
-- BlastN Tasks
  (1, 1, 'blastn')
, (2, 1, 'blastn-short')
, (3, 1, 'dc-megablast')
, (4, 1, 'megablast')
, (5, 1, 'rmblast')

-- BlastP Tasks
, (6, 2, 'blastp')
, (7, 2, 'blastp-fast')
, (8, 2, 'blastp-short')

-- BlastX Tasks
, (9, 3, 'blastx')
, (10, 3, 'blastx-fast')

-- TBlastN Tasks
, (11, 4, 'tblastn')
, (12, 4, 'tblastn-fast')
;
