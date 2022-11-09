package org.veupathdb.service.mblast.report.db

import org.veupathdb.lib.container.jaxrs.utils.db.DbManager
import org.veupathdb.lib.hash_id.HashID

object ReportDB {
  /**
   * Fetches all report jobs attached to the user with the given ID.
   *
   * @param userID ID of the user whose report jobs should be returned.
   *
   * @return A list of zero or more jobs attached to the target user ID.
   */
  fun selectReportJobs(userID: Long) =
    withTransaction { it.selectReportJobs(userID) }

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
  fun selectReportJobs(queryJobID: HashID, userID: Long) =
    withTransaction { it.selectReportJobs(queryJobID, userID) }

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
  fun selectReportJob(reportJobID: HashID, userID: Long) =
    withTransaction { it.selectReportJob(reportJobID, userID) }

  /**
   * Fetches a target report job if it exists.
   *
   * If the job does not exist, `null` will be returned.
   *
   * @param reportJobID ID of the report job to look up.
   *
   * @return Either the target job record, if it exists, or `null`.
   */
  fun selectReportJob(reportJobID: HashID) =
    withTransaction { it.selectReportJob(reportJobID) }

  fun userIsGuest(userID: Long): Boolean? =
    withTransaction { it.userIsGuest(userID) }

  fun updateUserLinksOwner(oldUserID: Long, newUserID: Long) =
    withTransaction { it.updateUserLinksOwner(oldUserID, newUserID) }

  fun deleteUserLink(reportJobID: HashID, userID: Long) =
    withTransaction { it.deleteUserLink() }

  /**
   * Executes the given function in the context of the provided open database
   * transaction.
   *
   * @param fn Function to execute in the transaction.
   *
   * @param R the return value of the given function.
   *
   * @return The result value of the execution of the given function.
   */
  fun <R> withTransaction(fn: (tx: ReportDBTX) -> R): R {
    val tx = ReportDBTX(DbManager.userDatabase().dataSource.connection)
    try {
      return fn(tx).also { tx.commit() }
    } catch (e: Throwable) {
      tx.rollback()
      throw e
    } finally {
      tx.close()
    }
  }
}