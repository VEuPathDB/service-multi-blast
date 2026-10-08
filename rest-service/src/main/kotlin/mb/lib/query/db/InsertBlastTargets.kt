package mb.lib.query.db

import mb.lib.query.model.JobTarget
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection

@Suppress("ArrayInDataClass")
data class InsertBlastTargets(
  private val con: Connection,
  private val jobID: HashID,
  private val dbs: Array<out JobTarget>,
) {
  companion object {
    // language=postgresql
    private const val Query = """
    INSERT INTO
      multiblast.multiblast_job_to_targets (job_digest, organism, target_file)
    VALUES
      (?, ?, ?)
    """
  }

  fun run() {
    con.prepareStatement(Query)!!.use {
      it.setBytes(1, jobID.bytes)

      for ((organism, target) in dbs) {
        it.setString(2, organism ?: "")
        it.setString(3, target)
        it.addBatch()
      }

      it.executeBatch()
    }
  }
}
