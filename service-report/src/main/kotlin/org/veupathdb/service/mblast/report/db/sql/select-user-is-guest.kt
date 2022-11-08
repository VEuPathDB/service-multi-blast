package org.veupathdb.service.mblast.report.db.sql

import org.veupathdb.service.mblast.report.db.Column
import org.veupathdb.service.mblast.report.db.Schema
import org.veupathdb.service.mblast.report.db.Table
import java.sql.Connection

private const val SQL = """
SELECT
  ${Column.IsGuest}
FROM
  ${Schema.Users}.${Table.Users}
WHERE
  ${Column.UserID} = ?
"""

/**
 * Selects whether the given target user ID belongs to a guest user.
 *
 * If no such user exists with the target user ID, `null` will be returned.
 *
 * @param userID ID of the user to test.
 *
 * @return The value of the `is_guest` flag for the target user (if the user
 * exists).
 */
fun Connection.selectUserIsGuest(userID: Long): Boolean? =
  prepareStatement(SQL).use { ps ->
    ps.setLong(1, userID)
    ps.executeQuery().use { rs ->
      if (rs.next())
        rs.getBoolean(Column.IsGuest)
      else
        null
    }
  }