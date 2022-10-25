package org.veupathdb.service.mblast.query.service.jobs.fetch

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.generated.model.QueryJobDetails
import org.veupathdb.service.mblast.query.mixins.toIODetails
import org.veupathdb.service.mblast.query.model.QueryUserMetaImpl
import org.veupathdb.service.mblast.query.service.MBlastPlatform
import org.veupathdb.service.mblast.query.sql.JobDBManager

/**
 * Looks up the target job and links it to the target user if it is not already
 * linked.
 *
 * This method throws a 404 exception if the target job doesn't exist.
 *
 * @param queryJobID ID of the query job to fetch and link.
 *
 * @param userID ID of the user to link to the target query job (if the user is
 * not already linked to that job)
 *
 * @throws NotFoundException If the target job doesn't exist.
 */
fun GetAndLinkJob(queryJobID: HashID, userID: Long): QueryJobDetails {
  // Attempt to look up the job already attached to this user.
  // If it exists, translate and return it.
  MBlastPlatform.getJob(queryJobID, userID)
    ?.let { (dbJob, s3Job) -> dbJob.toIODetails(s3Job) }
    ?.also { return it }

  // So the job isn't already linked to the target user.  The job may still
  // exist, in which case we will need to link it to the user.
  val job = MBlastPlatform.getJob(queryJobID)
    ?.let { (dbJob, s3Job) -> dbJob.toIODetails(s3Job) }
    ?: throw NotFoundException()

  JobDBManager.withTransaction { it.insertUserLink(queryJobID, QueryUserMetaImpl(userID, null, null)) }

  return job
}

/**
 * Looks up the target job.
 *
 * This method throws a 404 exception if the target job doesn't exist.
 *
 * @param queryJobID ID of the query job to fetch.
 *
 * @param userID ID of the user the job may belong to.
 *
 * @throws NotFoundException If the target job doesn't exist.
 */
fun GetJob(queryJobID: HashID, userID: Long) =
  (MBlastPlatform.getJob(queryJobID, userID) ?: MBlastPlatform.getJob(queryJobID))
    ?.let { (dbJob, s3Job) -> dbJob.toIODetails(s3Job) }
    ?: throw NotFoundException()


