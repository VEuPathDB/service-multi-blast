package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection

private const val SQL = """
DELETE FROM
  ${Schema.MBlast}.${Table.QueryToUsers}
WHERE
  ${Column.QueryJobID} = ?
  AND ${Column.UserID} = ?
"""

/**
 * Deletes the link between a target user and target query job.
 *
 * @param queryJobID ID of the job the user link should be removed from.
 *
 * @param userID ID of the user whose link should be removed.
 */
fun Connection.deleteUserLink(queryJobID: HashID, userID: Long) {
  prepareStatement(SQL).use { ps ->
    ps.setString(1, queryJobID.string)
    ps.setLong(2, userID)
    ps.execute()
  }
}