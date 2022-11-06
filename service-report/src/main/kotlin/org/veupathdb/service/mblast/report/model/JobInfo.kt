package org.veupathdb.service.mblast.report.model

import org.veupathdb.lib.compute.platform.job.AsyncJob
import org.veupathdb.lib.compute.platform.job.JobStatus

data class JobInfo(
  val db: ReportDetails,
  val s3: AsyncJob?
) {
  inline val reportJobID
    get() = db.reportJobID

  inline val queryJobID
    get() = db.queryJobID

  inline val config
    get() = db.config

  inline val status
    get() = s3?.status ?: JobStatus.Expired
}

