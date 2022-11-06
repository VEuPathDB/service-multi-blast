package org.veupathdb.service.mblast.report.service.jobs.submit

import jakarta.ws.rs.BadRequestException
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponse
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponseImpl
import org.veupathdb.service.mblast.report.service.ReportPlatform
import org.veupathdb.service.mblast.report.service.mblast.MBlastQuerySvc

/**
 * Handles the submission of a job that already exists in the user database.
 *
 * This method tests that the target query job is complete then submits the
 * report job to the queue.
 */
fun JobSubmission.handleSubmitJob(): JobCreateResponse {
  // Verify the query job is in the correct status.
  val queryJobStatus = try {
    MBlastQuerySvc.getQueryStatus(queryID, userAuth)
  } catch (e: MBlastQuerySvc.QueryJobNotFoundException) {
    throw BadRequestException(e)
  }

  // If the job status for the query is not "complete" then there's nothing we
  // can do.
  if (queryJobStatus != JobStatus.Complete)
    throw BadRequestException("Cannot run a report against a query job that has not successfully completed.")

  ReportPlatform.submitJob(reportID, queryID, userAuth, config)

  return JobCreateResponseImpl().also { it.reportJobID = reportID.string }
}


