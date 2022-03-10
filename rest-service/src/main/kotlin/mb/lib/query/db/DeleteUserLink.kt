package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement

/**
 * A query that deletes a user to blast job link.
 */
data class DeleteUserLink(
  private val con: Connection,
  private val jobID: HashID,
  private val userID: Long,
) {
  companion object {
    private const val Query = """
    DELETE FROM
      userlogins5.multiblast_users
    WHERE
      job_digest = ?
      AND user_id = ?
    """
  }

  /**
   * Execute the SQL query.
   */
  fun run() = BasicPreparedVoidQuery(Query, con, this::prep).execute()

  /**
   * Prepare the given statement with the stored values.
   *
   * @param ps SQL statement to prepare.
   */
  private fun prep(ps: PreparedStatement) {
    ps.setBytes(1, jobID.bytes)
    ps.setLong(2, userID)
  }
}
