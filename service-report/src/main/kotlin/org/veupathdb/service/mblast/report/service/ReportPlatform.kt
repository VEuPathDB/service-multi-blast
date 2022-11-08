package org.veupathdb.service.mblast.report.service

import org.gusdb.fgputil.Tuples.TwoTuple
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.Const
import org.veupathdb.service.mblast.report.ServiceOptions
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.model.JobConfig
import org.veupathdb.service.mblast.report.model.JobInfo
import org.veupathdb.service.mblast.report.model.UserJobInfo
import java.io.InputStream

/**
 * Facade over the [ReportDB] and [AsyncPlatform] objects that provides
 * functions for common tasks.
 */
object ReportPlatform {

  fun getJob(reportJobID: HashID): JobInfo? {
    val dbRow = ReportDB.selectReportJob(reportJobID)
    val s3Row = AsyncPlatform.getJob(reportJobID)

    if (s3Row != null && dbRow == null)
      throw IllegalStateException("Job $reportJobID exists in S3 but not in the user DB")

    if (dbRow == null)
      return null

    return JobInfo(dbRow, s3Row)
  }

  fun getJobFiles(reportJobID: HashID) = AsyncPlatform.getJobFiles(reportJobID)

  fun openJobFile(reportJobID: HashID, fileName: String) =
    getJobFiles(reportJobID)
      .stream()
      .filter { it.name == fileName }
      .findAny()
      .orElse(null)
      ?.open()

  fun getJobStatus(reportJobID: HashID): JobStatus {
    return AsyncPlatform.getJob(reportJobID)?.status ?: JobStatus.Expired
  }

  fun submitJob(
    reportJobID: HashID,
    queryJobID: HashID,
    userAuth: TwoTuple<String, String>,
    config: BlastFormatter,
  ) {
    AsyncPlatform.submitJob(ServiceOptions.jobQueueName) {
      this.jobID  = reportJobID
      this.config = JobConfig(
        queryJobID,
        userAuth
      ).toJson()
      this.addInputFile(Const.CONFIG_FILE, config.toJson().toString().reader())
    }
  }
}