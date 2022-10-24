package mblast.migration.db.sql

import org.intellij.lang.annotations.Language
import java.sql.Connection

@Language("Oracle")
private const val InsertTargetsSQL = """
INSERT INTO
  mblast.query_to_targets (
    query_job_id
  , target_name
  , target_file
  )
SELECT
  LOWER(RAWTOHEX(job_digest))
, organism
, target_file
FROM
  userlogins5.multiblast_job_to_targets
"""

fun Connection.insertQueryTargets() {
  createStatement().use { it.execute(InsertTargetsSQL) }
}
