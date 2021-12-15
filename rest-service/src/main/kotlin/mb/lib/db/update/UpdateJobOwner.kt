package mb.lib.db.update

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.db.constants.SQL.Update.MultiBlastUsers.Owner
import java.sql.Connection
import java.sql.PreparedStatement

/**
 * Query for updating user-to-job links from one user ID to another.
 */
data class UpdateJobOwner(
  private val con: Connection,
  private val oldID: Long,
  private val newID: Long,
) {
  fun run() = BasicPreparedVoidQuery(Owner, con, this::prepare).execute()

  fun prepare(ps: PreparedStatement) {
    ps.setLong(1, newID)
    ps.setLong(2, oldID)
  }
}
