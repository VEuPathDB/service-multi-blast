package org.veupathdb.service.mblast.query.service.cache

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.AsyncJob
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.Const

/**
 * Get Job Query File
 *
 * Returns the query file for the job with the given ID.  If no such job exists
 * with the given ID, this method will throw a [NotFoundException].
 *
 * @param queryJobID ID for the job whose query to get.
 *
 * @return An input stream over the contents of the target job's query file.
 */
fun GetJobQuery(queryJobID: HashID) =
  AsyncPlatform.getJobOr404(queryJobID)
    .ifFinishedOr403 { getFileOrFail(Const.QueryFileName).open() }


/**
 * Get Job Result File
 *
 * Returns the report file for the job with the given ID.  If no such job exists
 * with the given ID, this method will throw a [NotFoundException].
 *
 * @param queryJobID ID for the job whose report to get.
 *
 * @return An input stream over the contents of the target job's report file.
 */
fun GetJobResult(queryJobID: HashID) =
  AsyncPlatform.getJobOr404(queryJobID)
    .ifFinishedOr403 { getFileOrFail(Const.ReportFileName).open() }

/**
 * Executes the given function if the job is finished, otherwise throws a
 * [ForbiddenException].
 *
 * @param fn Function to execute if the job has finished.
 *
 * @param R Return type of the callback function.
 *
 * @return The return value from the callback function.
 */
private inline fun <R> AsyncJob.ifFinishedOr403(fn: AsyncJob.() -> R) =
  if (status.isFinished)
    fn()
  else
    throw ForbiddenException("Job has not yet finished!")

private inline fun AsyncJob.getFileOrFail(file: String) =
  AsyncPlatform.getJobFiles(jobID)
    .firstOrNull { it.name == file }
    ?: throw IllegalStateException("Could not fetch file $file for job $jobID")

private inline fun AsyncPlatform.getJobOr404(queryJobID: HashID) =
  getJob(queryJobID) ?: throw NotFoundException()
