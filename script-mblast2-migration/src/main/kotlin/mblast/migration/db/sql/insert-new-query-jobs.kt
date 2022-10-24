package mblast.migration.db.sql

import mblast.migration.db.model.QueryConfigsRow
import org.intellij.lang.annotations.Language
import java.sql.Connection

@Language("Oracle")
private const val InsertJobsSQL = """
INSERT INTO
  mblast.query_configs (
    query_job_id
  , project_id
  , config
  , query
  , created_on
  )
VALUES
  (?, ?, ?, ?, ?)
"""

class QueryJobInserter(con: Connection) : AutoCloseable {

  private val configPS = con.prepareStatement(InsertJobsSQL)

  fun addQueryConfig(row: QueryConfigsRow) {
    configPS.setString(1, row.queryJobID.string)
    configPS.setString(2, row.projectID)
    configPS.setString(3, row.config.toJson().toString())
    configPS.setString(4, row.query)
    configPS.setObject(5, row.createdOn)
    configPS.addBatch()
  }

  fun execute() {
    configPS.executeBatch()
  }

  override fun close() {
    configPS.close()
  }
}