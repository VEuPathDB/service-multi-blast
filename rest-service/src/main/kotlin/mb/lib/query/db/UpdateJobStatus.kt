package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.model.JobStatus
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement

data class UpdateJobStatus(
  private val con: Connection,
  private val jobID: HashID,
  private val queueID: Int,
  private val status: JobStatus,
) {
  companion object {
    // language=postgresql
    private const val Query = """
    UPDATE
      multiblast.multiblast_jobs
    SET
      status = ?
    , queue_id = ?
    WHERE
      job_digest = ?
    """
  }

  fun run() {
    BasicPreparedVoidQuery(Query, con, this::prep).execute()
  }

  private fun prep(ps: PreparedStatement) {
    ps.setString(1, status.value)
    ps.setInt(2, queueID)
    ps.setBytes(3, jobID.bytes)
  }
}
