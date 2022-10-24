package mblast.migration.db.sql

import org.intellij.lang.annotations.Language
import java.sql.Connection


@Language("Oracle")
private const val InsertQueryUserLinks = """
INSERT INTO
  mblast.query_to_users (
    query_job_id
  , user_id
  , summary
  , description
  )
SELECT
  LOWER(RAWTOHEX(job_digest))
, user_id
, ''
, description
FROM
  userlogins5.multiblast_users
WHERE
  run_directly = 1
"""

fun Connection.insertQueryUserLinks() {
  createStatement().use { it.execute(InsertQueryUserLinks) }
}