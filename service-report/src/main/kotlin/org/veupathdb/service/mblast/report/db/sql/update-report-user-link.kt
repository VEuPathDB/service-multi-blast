package org.veupathdb.service.mblast.report.db.sql

import org.intellij.lang.annotations.Language
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.Column
import org.veupathdb.service.mblast.report.db.Schema
import org.veupathdb.service.mblast.report.db.Table
import org.veupathdb.service.mblast.report.db.executeWithRaceCheck
import java.sql.Connection

@Language("Oracle")
private const val SQL = """
UPDATE
  ${Schema.MBlast}.${Table.ReportToUsers}
SET
  ${Column.Summary} = ?
, ${Column.Description} = ?
WHERE
  ${Column.ReportJobID} = ?
  AND ${Column.UserID} = ?
"""

/**
 * Inserts a link between a report job and a target user.
 *
 * @param reportJobID ID of the report job the user should be linked to.
 *
 * @param userID ID of the user to link.
 *
 * @param summary Optional user provided summary of the report job.
 *
 * @param description Optional user provided description of the report job.
 */
fun Connection.updateReportUserLink(
  reportJobID: HashID,
  userID: Long,
  summary: String?,
  description: String?
) {
  prepareStatement(SQL).use { ps ->
    ps.setString(1, summary)
    ps.setString(2, description)
    ps.setString(3, reportJobID.string)
    ps.setLong(4, userID)
    ps.execute()
  }
}