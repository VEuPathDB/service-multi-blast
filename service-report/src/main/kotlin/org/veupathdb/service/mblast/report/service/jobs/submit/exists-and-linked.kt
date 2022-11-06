package org.veupathdb.service.mblast.report.service.jobs.submit

import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponse
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponseImpl
import org.veupathdb.service.mblast.report.service.ReportPlatform

/**
 * Handles the job submission case where the report job already exists and is
 * linked to the requesting user.
 *
 * This method determines the current state of the job, and if the job is
 * expired, resubmits it.
 *
 * @receiver Job submission details.
 *
 * @return Job creation response object.
 */
fun JobSubmission.handleJobExistsAndIsLinked(): JobCreateResponse {
  // Look up the current job status to see what we should do.
  when (ReportPlatform.getJobStatus(reportID)) {
    // It's either done and ready or still running, so we can return here.
    JobStatus.Queued,
    JobStatus.InProgress,
    JobStatus.Complete,
    JobStatus.Failed,
    -> return JobCreateResponseImpl().also { it.reportJobID = reportID.string }

    // Ugh, it's expired, we gotta restart it.
    JobStatus.Expired,
    -> { /* Continue the function */ }
  }

  return handleSubmitJob()
}