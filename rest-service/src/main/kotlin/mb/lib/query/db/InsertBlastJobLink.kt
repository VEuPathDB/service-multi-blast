package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement

data class InsertBlastJobLink(private val con: Connection, private val childID: HashID, private val parentID: HashID) {
  companion object {
    private const val Query = """
    INSERT INTO
      userlogins5.multiblast_job_to_jobs (
        job_digest,
        parent_digest,
        position
      )
    VALUES
      (?, ?, (
        SELECT
          COALESCE(MAX(position), 0) + 1
        FROM
          userlogins5.multiblast_job_to_jobs
        WHERE
          parent_digest = ?
        )
      )
    """
  }

  fun run() = BasicPreparedVoidQuery(Query, con, this::prep).execute()

  private fun prep(ps: PreparedStatement) {
    ps.setBytes(1, childID.bytes)
    ps.setBytes(2, parentID.bytes)
    ps.setBytes(3, parentID.bytes)
  }
}
