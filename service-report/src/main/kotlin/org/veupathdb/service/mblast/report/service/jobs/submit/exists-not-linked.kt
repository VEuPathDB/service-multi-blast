package org.veupathdb.service.mblast.report.service.jobs.submit

import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponse

/**
 * Handles the job submission case where the job already exists, but is not yet
 * linked to the requesting user.
 *
 * This method links the user to the job, then determines the job's status and
 * resubmits the job to the queue if necessary.
 *
 * @receiver Job submission details.
 *
 * @return Job creation response object.
 */
fun JobSubmission.handleJobExistsAndIsNotLinked(): JobCreateResponse {
  if (addToUserCollection)
    ReportDB.withTransaction { it.insertReportUserLink(reportID, userID, userSummary, userDescription) }

  return handleJobExistsAndIsLinked()
}