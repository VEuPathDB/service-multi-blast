package mblast.migration.db.sql

import org.intellij.lang.annotations.Language
import java.sql.Connection

@Language("Oracle")
private const val SQL = """
INSERT INTO
  mblast.query_to_subqueries (
    parent_job_id
  , child_job_id
  , position
  )
SELECT
  LOWER(RAWTOHEX(parent_digest))
, LOWER(RAWTOHEX(job_digest))
, position
FROM
  userlogins5.multiblast_job_to_jobs
"""

fun Connection.insertQueryToSubQueryLinks() {
  createStatement().use { it.execute(SQL) }
}