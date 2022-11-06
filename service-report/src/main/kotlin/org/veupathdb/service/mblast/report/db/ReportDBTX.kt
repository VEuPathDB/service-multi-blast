package org.veupathdb.service.mblast.report.db

import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.sql.*
import org.veupathdb.service.mblast.report.model.ReportDetails
import org.veupathdb.service.mblast.report.model.UserMeta
import org.veupathdb.service.mblast.report.model.UserReportDetails
import java.sql.Connection

/**
 * Report Service DB Transaction
 *
 * Wrapper class around operations that the report service may need to perform
 * on the user database.
 */
class ReportDBTX(private val con: Connection) : AutoCloseable {
  init {
    con.autoCommit = false
  }

  fun selectReportJobs(userID: Long): List<UserReportDetails> =
    con.selectReportJobs(userID)

  fun selectReportJobs(queryJobID: HashID, userID: Long): List<UserReportDetails> =
    con.selectReportJobs(queryJobID, userID)

  fun selectReportJob(reportJobID: HashID, userID: Long): UserReportDetails? =
    con.selectReportJob(reportJobID, userID)

  fun selectReportJob(reportJobID: HashID): ReportDetails? =
    con.selectReportJob(reportJobID)

  fun insertReportJob(reportJobID: HashID, queryJobID: HashID, config: BlastFormatter) =
    con.insertReportJob(reportJobID, queryJobID, config)

  fun insertReportUserLink(reportJobID: HashID, userID: Long, summary: String?, description: String?) =
    con.insertReportUserLink(reportJobID, userID, summary, description)

  fun updateUserMeta(reportJobID: HashID, meta: UserMeta) =
    con.updateReportUserLink(reportJobID, meta.userID, meta.summary, meta.description)

  fun commit() {
    con.commit()
  }

  fun rollback() {
    con.rollback()
  }

  override fun close() {
    con.close()
  }
}