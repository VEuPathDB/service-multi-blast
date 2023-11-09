package org.veupathdb.service.mblast.report.db.sql

import org.intellij.lang.annotations.Language
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.Column
import org.veupathdb.service.mblast.report.db.Schema
import org.veupathdb.service.mblast.report.db.Table
import org.veupathdb.service.mblast.report.db.executeWithRaceCheck
import java.sql.Connection
import java.time.OffsetDateTime

@Language("Oracle")
private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.ReportConfigs} (
    ${Column.ReportJobID}
  , ${Column.QueryJobID}
  , ${Column.Config}
  , ${Column.CreatedOn}
  )
VALUES
  (?, ?, ?, ?)
"""

/**
 * Inserts a new report job record.
 *
 * @param reportJobID ID of the report job to insert.
 *
 * @param queryJobID ID of the query job the report job is for.
 *
 * @param config Blast formatter configuration.
 */
fun Connection.insertReportJob(
  reportJobID: HashID,
  queryJobID: HashID,
  config: BlastFormatter
) {
  prepareStatement(SQL).use { ps ->
    ps.setString(1, reportJobID.string)
    ps.setString(2, queryJobID.string)
    ps.setString(3, config.toJson().toString())
    ps.setObject(4, OffsetDateTime.now())
    ps.executeWithRaceCheck()
  }
}