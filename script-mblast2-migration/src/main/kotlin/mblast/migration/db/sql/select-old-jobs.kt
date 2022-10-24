package mblast.migration.db.sql

import mblast.migration.db.ResultSetIterator
import mblast.migration.db.model.MultiBlastJobToTargetsRow
import mblast.migration.db.model.MultiBlastJobsRow
import mblast.migration.db.stream
import org.intellij.lang.annotations.Language
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import java.sql.Connection
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.util.stream.Stream


@Language("Oracle")
private const val SQL = """
SELECT
  job_digest
, job_config
, query
, project_id
, created_on
FROM
  userlogins5.multiblast_jobs
ORDER BY
  job_digest
"""


fun Connection.selectOldJobConfigStream(): ResultSetIterator<MultiBlastJobsRow> =
  ResultSetIterator(createStatement().executeQuery(SQL)) { parseMultiBlastJobRow() }

private fun ResultSet.parseMultiBlastJobRow() =
  MultiBlastJobsRow(
    HashID(getBytes(1)),
    Json.parse(getString(2)),
    getString(3),
    getString(4),
    getObject(5, OffsetDateTime::class.java),
  )

