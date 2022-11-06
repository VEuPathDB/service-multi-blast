package org.veupathdb.service.mblast.report.db.sql

import org.intellij.lang.annotations.Language
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.Schema
import org.veupathdb.service.mblast.report.db.Table
import org.veupathdb.service.mblast.report.db.executeWithRaceCheck
import java.sql.Connection

@Language("Oracle")
private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.ReportToUsers} (
    report_job_id
  , user_id
  , summary
  , description
  )
VALUES
 (?, ?, ?, ?)
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
fun Connection.insertReportUserLink(
  reportJobID: HashID,
  userID: Long,
  summary: String?,
  description: String?
) {
  prepareStatement(SQL).use { ps ->
    ps.setString(1, reportJobID.string)
    ps.setLong(2, userID)
    ps.setString(3, summary)
    ps.setString(4, description)
    ps.executeWithRaceCheck()
  }
}