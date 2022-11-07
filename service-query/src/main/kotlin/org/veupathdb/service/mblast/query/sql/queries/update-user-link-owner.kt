package org.veupathdb.service.mblast.query.sql.queries

import org.intellij.lang.annotations.Language
import java.sql.Connection

@Language("Oracle")
private const val SQL = """
UPDATE
  mblast.query_to_users a
SET
  user_id = ?
WHERE
  user_id = ?
  AND NOT EXISTS (
    SELECT
      1
    FROM
      mblast.query_to_users b
    WHERE
      a.query_job_id = b.query_job_id
      AND b.user_id = ?
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