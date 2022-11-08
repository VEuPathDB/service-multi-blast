package org.veupathdb.service.mblast.report.db

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.service.mblast.report.model.ReportDetails
import org.veupathdb.service.mblast.report.model.UserReportDetails
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.time.OffsetDateTime

/**
 * Executes the target [PreparedStatement], swallowing unique constraint
 * violation exceptions.
 *
 * @receiver `PreparedStatement` to execute.
 */
fun PreparedStatement.executeWithRaceCheck() {
  try {
    execute()
  } catch (e: SQLException) {
    // Swallow the error if it's a unique constraint violation.
    if (e.errorCode != ORA_CODE_UNIQUE_VIOLATION)
      throw e
  }
}

/**
 * Uses the given function to map the rows in the target [ResultSet] into
 * entries in a new list.
 *
 * The entries in the list will be in the order they appeared in the target
 * [ResultSet].
 *
 * @param preSize Initialization size for the list that will be populated by the
 * target `ResultSet`.
 *
 * @param fn Mapping function that will parse individual rows in the `ResultSet`
 * into items for the returned list.
 *
 * @receiver Target `ResultSet` to iterate over.
 *
 * @return A list of values parsed from the target `ResultSet`.
 */
fun <R> ResultSet.toList(preSize: Int = 16, fn: ResultSet.() -> R): List<R> {
  val out = ArrayList<R>(preSize)

  while (next())
    out.add(fn())

  return out
}


fun ResultSet.parseReportDetails() =
  ReportDetails(
    HashID(getString(Column.ReportJobID)),
    HashID(getString(Column.QueryJobID)),
    Blast.blastFormatter(Json.parse<ObjectNode>(getString(Column.Config))),
    getObject(Column.CreatedOn, OffsetDateTime::class.java)
  )


fun ResultSet.parseUserReportDetails() =
  UserReportDetails(
    HashID(getString(Column.ReportJobID)),
    HashID(getString(Column.QueryJobID)),
    getLong(Column.UserID),
    Blast.blastFormatter(Json.parse<ObjectNode>(getString(Column.Config))),
    getObject(Column.CreatedOn, OffsetDateTime::class.java),
    getString(Column.Summary),
    getString(Column.Description)
  )
