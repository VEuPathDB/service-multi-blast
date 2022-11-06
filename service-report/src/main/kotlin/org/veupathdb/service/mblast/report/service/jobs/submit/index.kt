package org.veupathdb.service.mblast.report.service.jobs.submit

import org.gusdb.fgputil.Tuples.TwoTuple
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.convert.toInternal
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.generated.model.JobCreateResponse
import org.veupathdb.service.mblast.report.generated.model.ReportJobPostRequest
import org.veupathdb.service.mblast.report.service.hash.HashConfig
import org.veupathdb.service.mblast.report.valid.validate

fun SubmitJob(request: ReportJobPostRequest, userID: Long, authHeader: TwoTuple<String, String>): JobCreateResponse {
  request.validate()

  val submission = newJobSubmission(request, userID, authHeader)

  // Attempt to fetch this specific job from the user's job list.
  ReportDB.selectReportJob(submission.reportID, userID)?.let {
    // Job exists and is already linked to the user.
    return submission.handleJobExistsAndIsLinked()
  }

  // Attempt to fetch this specific job from the db at all
  ReportDB.selectReportJob(submission.reportID)?.let {
    // Job exists, but is not linked to the user.
    return submission.handleJobExistsAndIsNotLinked()
  }

  // Job just doesn't exist yet
  return submission.handleJobNotExists()
}

private fun newJobSubmission(request: ReportJobPostRequest, userID: Long, authHeader: TwoTuple<String, String>): JobSubmission {
  val config = request.blastConfig.toInternal()
  val queryID = HashID(request.queryJobID)

  return JobSubmission(
    HashConfig(config, queryID),
    queryID,
    config,
    userID,
    authHeader,
    request.userMeta?.summary,
    request.userMeta?.description,
    request.addToUserCollection ?: true // default to true as per API docs
  )
}
