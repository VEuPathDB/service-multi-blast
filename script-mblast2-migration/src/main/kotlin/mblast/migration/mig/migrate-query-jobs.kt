package mblast.migration.mig

import mblast.migration.conv.getBlastConfig
import mblast.migration.db.model.QueryConfigsRow
import mblast.migration.db.sql.QueryJobInserter
import mblast.migration.db.sql.selectOldJobConfigStream
import java.sql.Connection
import kotlin.time.measureTime

fun _migrateQueryJobs(con: Connection) {
  println("Migrating job records")

  val inserter = QueryJobInserter(con)
  var counter = 0
  val startTime = System.currentTimeMillis()

  val jobRS = con.selectOldJobConfigStream()

  println("ResultSet retrieved in ${(System.currentTimeMillis() - startTime) / 1000}s")

  while (jobRS.hasNext()) {
    val job = jobRS.next()

    try {
      inserter.addQueryConfig(
        QueryConfigsRow(
          job.jobDigest,
          job.projectID,
          job.getBlastConfig(),
          job.query,
          job.createdOn
        )
      )
    } catch (e: Exception) {
      println("Failed on job: ${job.jobDigest}")
      throw e
    }

    counter++
    if (counter % 100 == 0)
      println("Processed $counter jobs in ${(System.currentTimeMillis() - startTime) / 1000}s")
  }

  println("executing bulk insert of $counter jobs")
  inserter.execute()
  inserter.close()
}
