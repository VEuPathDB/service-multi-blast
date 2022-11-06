package org.veupathdb.service.mblast.report.service.jobs.submit

import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponse

/**
 * Handles the job submission case where the report job does not yet exist at
 * all.
 *
 * This method will create a record for the new report job, link the user to the
 * new report job, then submit the job to the queue.
 */
fun JobSubmission.handleJobNotExists() : JobCreateResponse {
  return ReportDB.withTransaction { db ->
    db.insertReportJob(reportID, queryID, config)
    db.insertReportUserLink(reportID, userID, userSummary, userDescription)

    handleSubmitJob()
  }
}