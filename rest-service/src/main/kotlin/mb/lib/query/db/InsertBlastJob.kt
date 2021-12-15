package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.query.model.BlastRow
import mb.lib.util.jsonStringify
import java.sql.Connection
import java.sql.PreparedStatement
import java.time.OffsetDateTime

data class InsertBlastJob(private val con: Connection, private val row: BlastRow) {
  companion object {
    private const val Query = """
    INSERT INTO
      userlogins5.multiblast_jobs (
        job_digest  -- 1
      , job_config
      , query
      , queue_id
      , project_id  -- 5
      , status
      , created_on
      , delete_on
      )
    VALUES
      (?, ?, ?, ?, ?, ?, ?, ?)
    """
  }
  fun run() = BasicPreparedVoidQuery(Query, con, this::prep).execute()

  private fun prep(ps: PreparedStatement) {
    val time = OffsetDateTime.now()
    val json = row.config!!.toJSON(true).jsonStringify()

    ps.setBytes(1, row.jobID.bytes)
    ps.setString(2, json)
    ps.setString(3, row.query)
    ps.setInt(4, row.queueID!!)
    ps.setString(5, row.projectID)
    ps.setString(6, row.status!!.value)
    ps.setObject(7, time)
    ps.setObject(8, time.plusDays(30))
  }
}
