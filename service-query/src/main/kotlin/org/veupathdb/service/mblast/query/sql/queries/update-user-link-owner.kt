package org.veupathdb.service.mblast.query.sql.queries

import org.intellij.lang.annotations.Language
import java.sql.Connection

@Language("Oracle")
private const val SQL = """
UPDATE
  ${Schema.MBlast}.${Table.QueryToUsers} a
SET
  ${Column.UserID} = ?
WHERE
  ${Column.UserID} = ?
  AND NOT EXISTS (
    SELECT
      1
    FROM
      ${Schema.MBlast}.${Table.QueryToUsers} b
    WHERE
      a.${Column.QueryJobID} = b.${Column.QueryJobID}
      AND b.${Column.UserID} = ?
  )
"""

/**
 * Updates the `user_id` field on all user links to be [newUserID] where they
 * were previously [oldUserID].
 *
 * If the target [newUserID] already has a link to a specific job, the link
 * ownership record for that job will not be updated.
 *
 * @param oldUserID User ID of the link owner before this method was called.
 *
 * @param newUserID User ID of the link owner after this method was called.
 */
fun Connection.updateUserLinksOwner(oldUserID: Long, newUserID: Long) {
  prepareStatement(SQL).use {
    it.setLong(1, newUserID)
    it.setLong(2, oldUserID)
    it.setLong(3, newUserID)
    it.execute()
  }
}