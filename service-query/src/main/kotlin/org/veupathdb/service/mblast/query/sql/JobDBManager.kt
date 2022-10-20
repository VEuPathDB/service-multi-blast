package org.veupathdb.service.mblast.query.sql

import org.veupathdb.lib.container.jaxrs.utils.db.DbManager
import org.veupathdb.lib.hash_id.HashID

/**
 * Job Database Manager
 *
 * Provides convenience methods for single queries against the database and
 * a session context in which multiple operations may be performed.
 *
 * @see JobDBTransaction
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 */
object JobDBManager {

  /**
   * Fetches a `FullParentQueryConfig` from the database for the given job ID if
   * such a record exists.
   *
   * If the target record does not exist, this method returns `null`.
   *
   * @param queryJobID ID of the job config to fetch.
   *
   * @return Either the full job record, if it exists, or `null` if it does not.
   */
  fun getFullJob(queryJobID: HashID) =
    JobDBTransaction(getConnection()).use { it.getFullJob(queryJobID)  }

  /**
   * Fetches a `FullParentUserQueryConfig` from the database for the given
   * combination of job ID and user ID, if such a record exists.
   *
   * If the target job does not exist, or is not linked to the target user, this
   * method returns `null`.
   *
   * @param queryJobID ID of the job config to fetch.
   *
   * @param userID ID of the user who the job must be linked to.
   *
   * @return Either the full job record including user metadata, or `null` if
   * no such record exists.
   */
  fun getFullUserJob(queryJobID: HashID, userID: Long) =
    JobDBTransaction(getConnection()).use { it.getFullUserJob(queryJobID, userID) }

  /**
   * Fetches a list of `FullParentUserQueryConfig`s from the database for the
   * given user ID.
   *
   * @param userID ID of the user whose job configs should be fetched.
   *
   * @return A list of zero or more job configs linked to the target user.
   */
  fun getFullUserJobs(userID: Long) =
    JobDBTransaction(getConnection()).use { it.getFullUserJobs(userID) }

  /**
   * Deletes the target link between a user and a query job.
   *
   * @param queryJobID ID of the job from which the link should be deleted.
   *
   * @param userID ID of the user whose link should be deleted.
   */
  fun deleteUserLink(queryJobID: HashID, userID: Long) =
    JobDBTransaction(getConnection()).use { it.deleteUserLink(queryJobID, userID) }

  /**
   * Executes the given function in the context of a database transaction that
   * will be committed on successful return.
   *
   * If the given function throws an exception, the transaction will be rolled
   * back.
   *
   * @param fn Function to execute.
   */
  fun withTransaction(fn: (JobDBTransaction) -> Unit) =
    JobDBTransaction(getConnection()).use(fn)

  private fun getConnection() = DbManager.userDatabase()
    .dataSource
    .connection
}

