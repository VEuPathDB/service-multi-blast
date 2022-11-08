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

  /**
   * Fetches all report jobs attached to the user with the given ID.
   *
   * @param userID ID of the user whose report jobs should be returned.
   *
   * @return A list of zero or more jobs attached to the target user ID.
   */
  fun selectReportJobs(userID: Long): List<UserReportDetails> =
    con.selectReportJobs(userID)

  /**
   * Fetches all report jobs attached to both the query job with the given job
   * ID and the user with the given user ID.
   *
   * @param queryJobID ID of the query job whose report jobs should be returned.
   *
   * @param userID ID of the user whose report jobs should be returned.
   *
   * @return A list of zero or more jobs attached to the target query job and
   * user.
   */
  fun selectReportJobs(queryJobID: HashID, userID: Long): List<UserReportDetails> =
    con.selectReportJobs(queryJobID, userID)

  /**
   * Fetches a target report job if it exists and is linked to the given target
   * user ID.
   *
   * If the job does not exist, or is not linked to the target user, `null` will
   * be returned.
   *
   * @param reportJobID ID of the report job to look up.
   *
   * @param userID ID of the target user the job should be linked to.
   *
   * @return Either the target job record, if it exists, or `null`.
   */
  fun selectReportJob(reportJobID: HashID, userID: Long): UserReportDetails? =
    con.selectReportJob(reportJobID, userID)

  /**
   * Fetches a target report job if it exists.
   *
   * If the job does not exist, `null` will be returned.
   *
   * @param reportJobID ID of the report job to look up.
   *
   * @return Either the target job record, if it exists, or `null`.
   */
  fun selectReportJob(reportJobID: HashID): ReportDetails? =
    con.selectReportJob(reportJobID)

  /**
   * Inserts a new report job record.
   *
   * @param reportJobID ID of the new report job to insert.
   *
   * @param queryJobID ID of the query job the new report job targets.
   *
   * @param config BLAST+ formatter configuration.
   */
  fun insertReportJob(reportJobID: HashID, queryJobID: HashID, config: BlastFormatter) =
    con.insertReportJob(reportJobID, queryJobID, config)

  /**
   * Inserts a new link between a report job and a user.
   *
   * @param reportJobID ID of the report job the user should be linked to.
   *
   * @param userID ID of the user to link.
   *
   * @param summary Optional job summary value to add to the link.
   *
   * @param description Optional description value to add to the link.
   */
  fun insertReportUserLink(reportJobID: HashID, userID: Long, summary: String?, description: String?) =
    con.insertReportUserLink(reportJobID, userID, summary, description)

  /**
   * Updates a link between a report job and a user.
   *
   * @param reportJobID ID of the report job the link to update targets.
   *
   * @param meta Updated user metadata to set on the link record.
   */
  fun updateUserMeta(reportJobID: HashID, meta: UserMeta) =
    con.updateReportUserLink(reportJobID, meta.userID, meta.summary, meta.description)

  fun userIsGuest(userID: Long) =
    con.selectUserIsGuest(userID)

  fun updateUserLinksOwner(oldUserID: Long, newUserID: Long) =
    con.updateUserLinksOwner(oldUserID, newUserID)

  /**
   * Commits this transaction.
   */
  fun commit() {
    con.commit()
  }

  /**
   * Rolls back this transaction.
   */
  fun rollback() {
    con.rollback()
  }

  /**
   * Closes the connection underlying this transaction.
   */
  override fun close() {
    con.close()
  }
}