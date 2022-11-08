package org.veupathdb.service.mblast.query.sql

import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.model.*
import org.veupathdb.service.mblast.query.sql.queries.*
import java.sql.Connection

/**
 * # Job DB Transaction Wrapped Operations
 *
 * Provides a collection of operations that may be performed on the job database
 * in a database transaction.
 *
 * The transaction must be either committed or rolled back when done.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 */
class JobDBTransaction(private val con: Connection) : AutoCloseable {

  private val logger = LogManager.getLogger(javaClass)

  init {
    con.autoCommit = false
  }

  /**
   * Inserts a query configuration into the database.
   *
   * @param config Config to insert.
   */
  fun insertQueryConfig(config: BasicQueryConfig) {
    logger.debug("insertQueryConfig(config=...)")
    con.insertQueryConfig(config)
  }

  /**
   * Inserts BLAST-target links for a target job.
   *
   * @param queryJobID ID of the job for which the BLAST-target links should be
   * inserted.
   *
   * @param targets BLAST-target links to insert.
   */
  fun insertTargetLinks(queryJobID: HashID, targets: List<BlastTarget>) {
    logger.debug("insertTargetLinks(queryJobID={}, targets=[...])", queryJobID)
    con.insertJobTargetLinks(queryJobID, targets)
  }

  /**
   * Inserts user meta for a target job.
   *
   * @param queryJobID ID of the job for which user metadata should be inserted.
   *
   * @param meta User metadata to insert.
   */
  fun insertUserLink(queryJobID: HashID, meta: QueryUserMeta) {
    logger.debug("insertUserLink(queryJobID={}, meta=...)", queryJobID)
    con.insertUserLink(queryJobID, meta)
  }

  /**
   * Updates the user meta for a target job.
   *
   * @param queryJobID ID of the job for which the user metadata should be
   * updated.
   *
   * @param meta User metadata to update with.
   */
  fun updateUserMeta(queryJobID: HashID, meta: QueryUserMeta) {
    logger.debug("updateUserMeta(queryJobID={}, meta=...)", queryJobID)
    con.updateUserLink(queryJobID, meta)
  }

  fun insertQueryToSubqueryLink(parentJobID: HashID, childJobID: HashID, position: UInt) =
    con.insertQueryToSubqueryLink(parentJobID, childJobID, position)

  /**
   * Fetches a [FullParentQueryConfig] for a target job if that job exists.
   *
   * @param queryJobID ID of the target job to fetch.
   *
   * @return Either the target job config, or `null` if the target job was not
   * found.
   */
  fun getFullJob(queryJobID: HashID): FullParentQueryConfig? {
    logger.debug("getFullJob(queryJobID={})", queryJobID)
    val parent   = con.selectQueryConfigByID(queryJobID) ?: return null
    val targets  = con.selectQueryTargetsByJob(queryJobID)
    val children = con.selectChildQueriesByParent(queryJobID)

    if (targets.isEmpty())
      throw IllegalStateException("Job $queryJobID has no targets!")

    return FullParentQueryConfigImpl(parent, targets, children)
  }

  /**
   * Fetches a [FullParentUserQueryConfig] for a target job and user if the job
   * exists and the user is linked to it.
   *
   * @param queryJobID ID of the target job to fetch.
   *
   * @param userID ID of the user who must be linked to the target job.
   *
   * @return Either the target job config and user metadata or `null` if the
   * target job was not found or was not linked to the target user.
   */
  fun getFullUserJob(queryJobID: HashID, userID: Long): FullParentUserQueryConfig? {
    logger.debug("getFullUserJob(queryJobID={}, userID={})", queryJobID, userID)
    val parent   = con.selectQueryConfigByJobAndUser(queryJobID, userID) ?: return null
    val targets  = con.selectQueryTargetsByJob(queryJobID)
    val children = con.selectChildQueriesByParent(queryJobID)

    if (targets.isEmpty())
      throw IllegalStateException("Job $queryJobID has no targets!")

    return FullParentUserQueryConfigImpl(parent, targets, children)
  }

  /**
   * Fetches all the [FullParentUserQueryConfig] records linked to a target
   * user.
   *
   * @param userID ID of the user whose jobs should be fetched.
   *
   * @param site Optional name of a target site used to further filter the
   * results.
   *
   * If this value is not `null`, only rows whose project ID matches this value
   * will be returned.
   *
   * @return A list of zero or more job configuration records.
   */
  fun getFullUserJobs(userID: Long, site: String? = null): List<FullParentUserQueryConfig> {
    logger.debug("getFullUserJobs(userID={}, site={})", userID, site)
    val parents  = site?.let { con.selectQueriesByUserAndSite(userID, it) }
      ?: con.selectQueriesByUser(userID)
    val targets  = con.selectQueryTargetsByUser(userID)
    val children = con.selectChildQueriesByUser(userID)

    return parents.map {
      val curTargets  = targets[it.queryJobID]
      val curChildren = children[it.queryJobID] ?: emptyList()

      if (curTargets.isNullOrEmpty())
        throw IllegalStateException("Job ${it.queryJobID} has no targets!")

      FullParentUserQueryConfigImpl(it, curTargets, curChildren)
    }
  }

  /**
   * Deletes the link between a target job and user.
   *
   * @param queryJobID ID of the target job.
   *
   * @param userID ID of the user whose link should be deleted.
   */
  fun deleteUserLink(queryJobID: HashID, userID: Long) {
    logger.debug("deleteUserLink(queryJobID={}, userID={})", queryJobID, userID)
    con.deleteUserLink(queryJobID, userID)
  }

  /**
   * Commits this transaction.
   */
  fun commit() {
    con.commit()
  }

  /**
   * Rolls this transaction back.
   */
  fun rollback() {
    con.rollback()
  }

  /**
   * Closes this transaction and it's underlying database connection.
   */
  override fun close() {
    con.close()
  }

  /**
   * Uses this transaction as the context in which the given function will be
   * executed.
   *
   * Upon successful return from the function, the transaction will be committed
   * and closed.
   *
   * On exception, the transaction will be rolled back and closed.
   *
   * @param fn Function to execute in this transaction.
   *
   * @param R Return type of the given function.
   *
   * @return The value returned by the given function.
   */
  inline fun <R> use(fn: (JobDBTransaction) -> R): R {
    try {
      val ret = fn(this)
      commit()
      return ret
    } catch (e: Exception) {
      rollback()
      throw e
    } finally {
      close()
    }
  }
}