package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.query.model.UserBlastRow
import java.sql.Connection
import java.sql.PreparedStatement

data class InsertUserLink(private val con: Connection, private val row: UserBlastRow) {
  companion object {
    // language=postgresql
    private const val Query = """
    INSERT INTO
      multiblast.multiblast_users (
        job_digest
      , user_id
      , description
      , max_download_size
      , run_directly
      )
    VALUES
      (?, ?, ?, ?, ?)
    ON CONFLICT DO NOTHING 
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
  }
}
