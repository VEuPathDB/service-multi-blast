package org.veupathdb.service.mblast.query.service.jobs.restart

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import org.veupathdb.lib.blast.common.fields.DBFiles
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.mixins.toDBFiles
import org.veupathdb.service.mblast.query.mixins.toInputStream
import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.service.BlastTargetManager
import org.veupathdb.service.mblast.query.service.MBlastPlatform


/**
 * Attempts to restart the target job if it is expired.
 *
 * If the target job does not exist, this method throws a 404 exception.
 *
 * If the target job exists but is not expired, this method throws a 403
 * exception.
 *
 * If the target job exists, but cannot be restarted due to the target BLAST+
 * database files no longer existing, this method throws a 410 exception.
 *
 * @param queryJobID ID of the job to restart.
 */
fun RestartJob(queryJobID: HashID) {
  val (dbJob, s3Job) = MBlastPlatform.getJob(queryJobID) ?: throw NotFoundException()

  // Root Job
  if ((s3Job?.status ?: JobStatus.Expired) != JobStatus.Expired)
    throw ForbiddenException("Cannot restart a non-expired job.")

  // Ensure the target databases still exist.
  dbJob.targets.verifyDBsStillExist(dbJob.projectID)

  // Append the targets to the config
  dbJob.config.dbFile = DBFiles(dbJob.targets.toDBFiles(dbJob.projectID))

  // Ensure the config has the required input/output flags
  dbJob.config.queryFile(Const.QueryFileName)
  dbJob.config.outFile(Const.ReportFileName)

  MBlastPlatform.queueParentJob(queryJobID, dbJob.config.toInputStream(), dbJob.queryFile)

  // With the child jobs
  dbJob.childJobs
    // Stream over the jobs
    .parallelStream()
    // Retrieve the S3 state for the job
    .map { Pair(it, MBlastPlatform.requireAsyncJob(it.queryJobID)) }
    // Filter the jobs down to only those that have expired
    .filter { (_, it) -> it.status == JobStatus.Expired }
    // Requeue the expired jobs
    .forEach { (it, _) -> MBlastPlatform.queueChildJob(it.queryJobID, it.config.toInputStream(), it.queryFile) }
}


private fun List<BlastTarget>.verifyDBsStillExist(site: String) {
  forEach {
    if (!BlastTargetManager.dbExists(site, it.displayName, it.fileName))
      throw WebApplicationException(Response.Status.GONE)
  }
}