CREATE TABLE multi_blast.tools
(
  tool_id SMALLINT    PRIMARY KEY NOT NULL
, name    VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO
  multi_blast.tools (tool_id, name)
VALUES
  (1, 'blastn')
, (2, 'blastp')
, (3, 'blastx')
, (4, 'tblastn')
, (5, 'tblastx')
, (6, 'rpsblast')
, (7, 'rpstblastn')
, (8, 'psiblast')
;
