package org.veupathdb.service.mblast.query.service.jobs.submit

import org.veupathdb.service.mblast.query.generated.model.JobCreateResponse
import org.veupathdb.service.mblast.query.generated.model.JobCreateResponseImpl
import org.veupathdb.service.mblast.query.generated.model.JobsPostMultipartFormData
import org.veupathdb.service.mblast.query.service.jobs.submit.conv.toJobSubmission
import org.veupathdb.service.mblast.query.service.jobs.submit.model.JobSubmission
import org.veupathdb.service.mblast.query.sql.JobDBManager


/**
 * Attempts to submit the job request to both the database and the async
 * platform, then links the job to the user with the given ID.
 *
 * If the request is invalid a 400 or 422 exception will be thrown.
 *
 * If a job with a matching config already exists and is already linked to the
 * target user, a 409 Conflict error will be thrown.
 *
 * If a job with a matching config already exists and is not already linked to
 * the target user, the existing job will be linked to the target user and
 * restarted if necessary.
 *
 * If a job with a matching config does not already exist, it will be created,
 * submitted to the queue, and linked to the target user.
 *
 * @param request Raw job request.
 *
 * @param userID ID of the user submitting the job.
 *
 * @return A job creation response object containing the ID for the submitted
 * job.
 */
fun SubmitJob(request: JobsPostMultipartFormData, userID: Long): JobCreateResponse =
  request.also { it.validate() }
    .toJobSubmission(userID)
    .use { it.also(::submitJob) }
    .let { JobCreateResponseImpl().apply { queryJobID = it.parentJobID.string } }


/**
 * Internal submit job.  Determines whether a job already exists matching the
 * job submission and calls the correct next phase of the process based on that.
 *
 * If the job exists and is already linked to the user, calls
 * [_handleJobExistsAndIsLinked].
 *
 * If the job exists and is not already linked to the user, calls
 * [_handleJobExistsButIsNotLinked].
 *
 * If no job matching this submission already exists, calls
 * [_handleJobNotExists].
 *
 * @param sub Job submission.
 */
private fun submitJob(sub: JobSubmission) {
  // If the user has already submitted this exact job
  JobDBManager.getFullUserJob(sub.parentJobID, sub.userMeta.userID)
    ?.use { return _handleJobExistsAndIsLinked(sub) }

  // So we know the user doesn't already have this job, but we don't know
  // whether the job exists in general or not.
  //
  // It probably doesn't, but let's check anyway just to be sure.
  JobDBManager.getFullJob(sub.parentJobID)
    ?.use { return _handleJobExistsButIsNotLinked(it) }

  // Okay, so the user doesn't have this job and the job itself just doesn't
  // exist.
  _handleJobNotExists(sub)
}
