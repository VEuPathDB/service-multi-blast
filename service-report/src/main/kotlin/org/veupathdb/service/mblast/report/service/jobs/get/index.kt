package org.veupathdb.service.mblast.report.service.jobs.get

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.ext.toIODetails
import org.veupathdb.service.mblast.report.generated.model.ReportJobDetails
import org.veupathdb.service.mblast.report.service.ReportPlatform


/**
 * # Get Single Job By ID
 *
 * Returns an optional single job looked up by the given [reportJobID] and
 * [userID].
 *
 * If the job was found but is not linked to the user represented by the given
 * [userID] then the user will be linked to the job before the job is returned.
 *
 * @param reportJobID ID of the report job to get.
 *
 * @param userID ID of the user that is either currently attached or will be
 * attached to the target job (if it exists).
 *
 * @param saveJob Whether the job should be saved to the user's job collection
 * (if it is not already saved).
 *
 * @return The target job, or `null` if no such job exists.
 */
fun GetJob(reportJobID: HashID, userID: Long, saveJob: Boolean): ReportJobDetails? {
  return ReportDB.withTransaction { db ->
    // Attempt to get the job from the user's collection directly.
    db.selectReportJob(reportJobID, userID)
      // It exists so return it.
      ?.also { return@withTransaction it.toIODetails() }

    // The user is not linked to the job.

    // Attempt to get the job itself
    db.selectReportJob(reportJobID)?.also {
      // the job exists

      // If the user has opted to save this job to their collection
      if (saveJob)
      // Then link the job to the user.
        db.insertReportUserLink(reportJobID, userID, null, null)

      // Return the job.
      return@withTransaction  it.toIODetails()
    }

    // No such job exists.
    null
  }
}


fun GetJobAdmin(queryJobID: HashID) =
  ReportPlatform.getJob(queryJobID)
    ?.let { (db, _) -> db.toIODetails() }
    ?: throw NotFoundException()