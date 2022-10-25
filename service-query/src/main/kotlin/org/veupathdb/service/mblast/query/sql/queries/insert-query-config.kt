package org.veupathdb.service.mblast.query.sql.queries

import org.intellij.lang.annotations.Language
import org.veupathdb.service.mblast.query.mixins.toReader
import org.veupathdb.service.mblast.query.model.BasicQueryConfig
import org.veupathdb.service.mblast.query.sql.util.insertWithRaceCheck
import java.sql.Connection
import java.sql.SQLException
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Language("Oracle")
private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.QueryConfigs} (
    ${Column.QueryJobID}
  , ${Column.ProjectID}
  , ${Column.Config}
  , ${Column.Query}
  , ${Column.CreatedOn}
  )
VALUES
  (?, ?, ?, ?, ?)
"""

/**
 * Inserts a new query configuration record into the permanent store database.
 *
 * If a unique constraint violation occurs while attempting to insert this
 * record, then it is most likely a race condition caused by the client/user
 * double submitting the job.  This function will handle that case and return
 * either `true` if the row was successfully inserted, or `false` if the record
 * already existed.
 *
 * @param config Basic configuration to insert into the database.
 *
 * @receiver Connection/transaction that this query will be executed on.
 *
 * @return `true` if a new record was inserted by this call.  `false` if there
 * was a unique constraint violation caused by this record insertion (meaning
 * the record already exists).
 *
 * @throws SQLException If an error occurs while attempting to execute this
 * query against the database.
 */
fun Connection.insertQueryConfig(config: BasicQueryConfig) =
  prepareStatement(SQL).use {
    val cReader = config.config.toReader()
    val qReader = config.getQueryReader()

    it.setString(1, config.queryJobID.string)
    it.setString(2, config.projectID)
    it.setClob(3, cReader)
    it.setClob(4, qReader)
    it.setObject(5, OffsetDateTime.now())

    try {
      it.insertWithRaceCheck()
    } finally {
      cReader.close()
      qReader.close()
    }
  }
