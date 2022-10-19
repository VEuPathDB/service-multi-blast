package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.model.QueryUserMeta
import java.sql.Connection

private const val SQL = """
UPDATE
  ${Schema.MBlast}.${Table.QueryToUsers}
SET
  ${Column.Summary} = ?
, ${Column.Description} = ?
WHERE
  ${Column.QueryJobID} = ?
  AND ${Column.UserID} = ?
"""

/**
 * Updates the metadata attached to a link between a query job and a user.
 *
 * @param queryJobID ID of the job for which the link will be updated.
 *
 * @param meta Metadata containing details about the link to update and it's new
 * values.
 */
fun Connection.updateUserLink(queryJobID: HashID, meta: QueryUserMeta) =
  prepareStatement(SQL).use { ps ->
    ps.setString(1, meta.summary)
    ps.setString(2, meta.description)
    ps.setString(3, queryJobID.string)
    ps.setLong(4, meta.userID)
    ps.executeUpdate()
  }