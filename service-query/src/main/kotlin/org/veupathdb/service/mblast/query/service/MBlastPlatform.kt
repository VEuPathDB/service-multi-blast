package org.veupathdb.service.mblast.query.service

import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.AsyncJob
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.ServiceOptions
import org.veupathdb.service.mblast.query.model.FullParentQueryConfig
import org.veupathdb.service.mblast.query.model.FullParentUserQueryConfig
import org.veupathdb.service.mblast.query.sql.JobDBManager
import java.io.File
import java.io.InputStream

/**
 * MBlast Platform
 */
object MBlastPlatform {

  fun getJob(queryJobID: HashID, userID: Long): Pair<FullParentUserQueryConfig, AsyncJob?>? {
    val dbJob = JobDBManager.getFullUserJob(queryJobID, userID)
      ?: return null
    val s3Job = AsyncPlatform.getJob(queryJobID)

    return Pair(dbJob, s3Job)
  }

  fun getJob(queryJobID: HashID): Pair<FullParentQueryConfig, AsyncJob?>? {
    val dbJob = JobDBManager.getFullJob(queryJobID)
    val s3Job = AsyncPlatform.getJob(queryJobID)

    validateState(queryJobID, dbJob, s3Job)

    return if (dbJob == null) null else Pair(dbJob, s3Job)
  }

  fun getJobFiles(queryJobID: HashID) =
    AsyncPlatform.getJobFiles(queryJobID)

  fun openJobFile(queryJobID: HashID, fileName: String) =
    getJobFiles(queryJobID)
      .find { it.name == fileName }
      ?.open()

  fun requireAsyncJob(queryJobID: HashID) =
    AsyncPlatform.getJob(queryJobID)
      ?: throw IllegalStateException("Job $queryJobID was required to be in S3 but was not found.")

  fun queueParentJob(queryJobID: HashID, config: InputStream, query: File) =
    queueJob(ServiceOptions.parentJobQueueName, queryJobID, config, query)

  fun queueChildJob(queryJobID: HashID, config: InputStream, query: File) =
    queueJob(ServiceOptions.childJobQueueName, queryJobID, config, query)

  fun queueJob(queue: String, queryJobID: HashID, config: InputStream, query: File) =
    AsyncPlatform.submitJob(queue) {
      jobID = queryJobID
      addInputFile(Const.ConfigFileName, config)
      addInputFile(Const.QueryFileName, query)
    }

  private fun validateState(id: HashID, db: Any?, s3: Any?) {
    if (db == null && s3 != null)
      throw IllegalStateException("Job $id exists in S3 but not the DB")
  }
}