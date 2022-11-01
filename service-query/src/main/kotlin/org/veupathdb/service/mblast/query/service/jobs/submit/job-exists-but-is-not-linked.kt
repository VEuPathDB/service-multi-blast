package org.veupathdb.service.mblast.query.service.jobs.submit

import org.veupathdb.lib.blast.common.fields.DBFiles
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.ServiceOptions
import org.veupathdb.service.mblast.query.mixins.toDBFiles
import org.veupathdb.service.mblast.query.model.BasicQueryConfig
import org.veupathdb.service.mblast.query.model.FullParentQueryConfig
import org.veupathdb.service.mblast.query.service.MBlastPlatform
import org.veupathdb.service.mblast.query.service.jobs.submit.model.JobSubmission
import org.veupathdb.service.mblast.query.sql.JobDBManager

fun _handleJobExistsButIsNotLinked(sub: JobSubmission, config: FullParentQueryConfig) {
  // If the user has addToUserCollection set to true, then the job should be linked to the user.
  if (sub.addToUserJobs) {
    JobDBManager.withTransaction { it.insertUserLink(config.queryJobID, sub.userMeta) }
  }

  // Expand the config for submission to the queue
  config.config.also {
    it.queryFile(Const.QueryFileName)
    it.outFile(Const.ReportFileName)
    it.dbFile = DBFiles(config.targets.toDBFiles(config.projectID))
  }

  submitJobToQueueIfExpired(ServiceOptions.parentJobQueueName, config)

  for (child in config.childJobs) {
    submitJobToQueueIfExpired(ServiceOptions.childJobQueueName, child)
  }
}

private fun submitJobToQueueIfExpired(queue: String, config: BasicQueryConfig) {
  // Lookup the job.  If no job was found, that's okay, someone probably wiped
  // the s3 cache, we can just resubmit the job either way.
  val status = AsyncPlatform.getJob(config.queryJobID)?.status
    ?: JobStatus.Expired

  if (status == JobStatus.Expired) {
    MBlastPlatform.queueJob(
      queue,
      config.queryJobID,
      config.config.toJson().toString().byteInputStream(),
      config.queryFile
    )
  }
}
