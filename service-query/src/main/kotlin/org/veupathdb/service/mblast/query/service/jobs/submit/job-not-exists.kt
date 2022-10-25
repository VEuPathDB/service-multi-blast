@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.service.mblast.query.service.jobs.submit

import mblast.util.ifExists
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.model.BasicQueryConfigImpl
import org.veupathdb.service.mblast.query.service.JobHasher
import org.veupathdb.service.mblast.query.service.MBlastPlatform
import org.veupathdb.service.mblast.query.service.jobs.submit.model.JobSubmission
import org.veupathdb.service.mblast.query.sql.JobDBManager
import org.veupathdb.service.mblast.query.sql.JobDBTransaction
import java.io.File

fun _handleJobNotExists(sub: JobSubmission) {
  // For the parent job
  verifyJobNotInMinio(sub.parentJobID)

  // Submit the root job.
  JobDBManager.withTransaction { db -> createParentJob(sub, db) }

  // For the child jobs
  sub.queryFiles.subQueryFiles.forEach { childQuery ->
    val childID = JobHasher.hashJob(sub.blastConfig.forDB, sub.projectID, childQuery, sub.blastTargets)

    val childExistsInDB = JobDBManager.getFullJob(childID)?.use { true } ?: false
    val childCacheJob = AsyncPlatform.getJob(childID)

    // If the child job exists in the database
    if (childExistsInDB) {
      // If the job is expired
      if ((childCacheJob?.status ?: JobStatus.Expired) == JobStatus.Expired) {
        // Resubmit the expired job
        sub.submitToChildQueue(childID, childQuery)
      }
    }

    // Else, if the child job DOES NOT exist in the database
    else {
      // If the job exists in the cache, throw an exception for inconsistent
      // state.
      childCacheJob.ifExists { throwJobInCacheNotDB(childID) }
      JobDBManager.withTransaction { db -> createChildJob(sub, db, childID, childQuery) }
    }
  }

  // Insert job to sub job links

}


private inline fun verifyJobNotInMinio(queryJobID: HashID) {
  AsyncPlatform.getJob(queryJobID).ifExists { throwJobInCacheNotDB(queryJobID) }
}

private fun createParentJob(sub: JobSubmission, db: JobDBTransaction) {
  db.insertQueryConfig(sub.newBasicQueryConfig(sub.parentJobID, sub.queryFiles.overallQueryFile))
  db.insertTargetLinks(sub.parentJobID, sub.blastTargets)

  if (sub.addToUserJobs)
    db.insertUserLink(sub.parentJobID, sub.userMeta)

  sub.submitToParentQueue()
}

private fun createChildJob(sub: JobSubmission, db: JobDBTransaction, jobID: HashID, query: File) {
  db.insertQueryConfig(sub.newBasicQueryConfig(jobID, query))
  db.insertTargetLinks(jobID, sub.blastTargets)

  sub.submitToChildQueue(jobID, query)
}

private inline fun throwJobInCacheNotDB(queryJobID: HashID): Nothing =
  throw IllegalStateException("Job $queryJobID exists in the cache but not in the database")

private inline fun JobSubmission.submitToParentQueue() =
  MBlastPlatform.queueParentJob(
    parentJobID,
    blastConfig.forQueue.toJson().toString().byteInputStream(),
    queryFiles.overallQueryFile
  )

private inline fun JobSubmission.submitToChildQueue(queryJobID: HashID, query: File) =
  MBlastPlatform.queueChildJob(
    queryJobID,
    blastConfig.forQueue.toJson().toString().byteInputStream(),
    query
  )

private inline fun JobSubmission.newBasicQueryConfig(jobID: HashID, queryFile: File) =
  BasicQueryConfigImpl(jobID, projectID, blastConfig.forDB, queryFile)
