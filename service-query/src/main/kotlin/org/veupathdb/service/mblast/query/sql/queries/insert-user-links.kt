package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.service.mblast.query.model.QueryUserMeta
import org.veupathdb.service.mblast.query.sql.util.insertWithRaceCheck
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection

private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.QueryToUsers} (
    ${Column.QueryJobID}
  , ${Column.UserID}
  , ${Column.Summary}
  , ${Column.Description}
  )
VALUES
  (?, ?, ?, ?)
"""

/**
 * Inserts a link and user metadata for a user and target query job.
 *
 * If a unique constraint violation occurs while attempting to insert this
 * record, then it is most likely a race condition caused by the client/user
 * double submitting the job.  This function will handle that case and return
 * either `true` if the row was successfully inserted, or `false` if the record
 * already existed.
 *
 * @param queryJobID ID of the target query job the user will be linked to.
 *
 * @param meta User metadata that will be inserted.
 *
 * @return `true` if the record was successfully inserted. `false` if the record
 * already existed.
 */
fun Connection.insertUserLink(queryJobID: HashID, meta: QueryUserMeta) =
  prepareStatement(SQL).use { ps ->
    ps.setString(1, queryJobID.string)
    ps.setLong(2, meta.userID)
    ps.setString(3, meta.summary)
    ps.setClob(4, meta.description?.reader())

    ps.insertWithRaceCheck()
  }