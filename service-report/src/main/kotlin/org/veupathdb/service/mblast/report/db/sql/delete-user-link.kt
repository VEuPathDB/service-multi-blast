package org.veupathdb.service.mblast.report.db.sql

import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.Column
import org.veupathdb.service.mblast.report.db.Schema
import org.veupathdb.service.mblast.report.db.Table
import java.sql.Connection

private const val SQL = """
DELETE FROM
  ${Schema.MBlast}.${Table.ReportToUsers}
WHERE
  ${Column.ReportJobID} = ?
  AND ${Column.UserID} = ?
"""

/**
 * Deletes the link between a target user and target report job.
 *
 * @param reportJobID ID of the job the user link should be removed from.
 *
 * @param userID ID of the user whose link should be removed.
 */
fun Connection.deleteUserLink(reportJobID: HashID, userID: Long) {
  prepareStatement(SQL).use { ps ->
    ps.setString(1, reportJobID.string)
    ps.setLong(2, userID)
    ps.execute()
  }
}