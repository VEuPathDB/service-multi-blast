package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.query.model.UserBlastRow
import java.sql.Connection
import java.sql.PreparedStatement

data class InsertUserLink(private val con: Connection, private val row: UserBlastRow) {
  companion object {
    private const val Query = """
    INSERT INTO
      multiblast.multiblast_users
    SELECT
      ?, ?, ?, ?, ?
    FROM
      dual
    WHERE
      NOT EXISTS (
        SELECT
          1
        FROM
          multiblast.multiblast_users
        WHERE
          job_digest = ?
        AND
          user_id = ?
      )
    """
  }

  fun run() = BasicPreparedVoidQuery(Query, con, this::prep).execute()

  private fun prep(ps: PreparedStatement) {
    // SELECT
    ps.setBytes(1, row.jobID.bytes)
    ps.setLong(2, row.userID)
    ps.setString(3, row.description)
    ps.setLong(4, row.maxDownloadSize)
    ps.setBoolean(5, row.runDirectly)
    // WHERE NOT EXISTS
    ps.setBytes(6, row.jobID.bytes)
    ps.setLong(7, row.userID)
  }
}
