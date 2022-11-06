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


fun PreparedStatement.executeWithRaceCheck() {
  try {
    execute()
  } catch (e: SQLException) {
    // Swallow the error if it's a unique constraint violation.
    if (e.errorCode != ORA_CODE_UNIQUE_VIOLATION)
      throw e
  }
}


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
