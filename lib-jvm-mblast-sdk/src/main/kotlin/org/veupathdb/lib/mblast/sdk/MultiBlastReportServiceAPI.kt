package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobBulkStatusResponse
import org.veupathdb.lib.mblast.sdk.except.MBlastAPIException
import org.veupathdb.lib.mblast.sdk.except.MBlastJobNotFoundException
import org.veupathdb.lib.mblast.sdk.report.model.*
import org.veupathdb.lib.mblast.sdk.util.Either
import java.io.InputStream

sealed interface MultiBlastReportServiceAPI {

  /**
   * Fetches the list of jobs associated with the configured client user from
   * the MultiBlast query service.
   *
   * The results may be optionally filtered to only results relating to the
   * specified [queryJobID].
   *
   * @param queryJobID Optional query job to filter results by.
   *
   * Defaults to `null`.
   *
   * @return A list of zero or more jobs associated with the configured client
   * user, optionally filtered by query job.
   */
  @Throws(MBlastAPIException::class)
  fun listJobs(queryJobID: HashID? = null): List<ReportJobListEntry>

  /**
   * Fetches the target job from the MultiBlast report service, if it exists.
   *
   * If the target job does not exist, `null` will be returned.
   *
   * @param jobID ID of the job to fetch.
   *
   * @param saveJob Whether the target job should be automatically added to the
   * configured client user's job collection.
   *
   * Defaults to `false`.
   *
   * @return Fetched details about the target job, if the job exists, otherwise
   * `null`.
   */
  @Throws(MBlastAPIException::class)
  fun getJob(jobID: HashID, saveJob: Boolean = false): ReportJobDetails?

  /**
   * Attempts to restart a target job in the MultiBlast report service.
   *
   * If the target job does not exist, this method will throw an exception.
   *
   * @param jobID ID of the target job to restart.
   */
  @Throws(MBlastJobNotFoundException::class, MBlastAPIException::class)
  fun restartJob(jobID: HashID)

  /**
   * Updates a target job.
   *
   * If the target job does not exist, this method will throw an exception.
   *
   * @param jobID ID of the target job to update.
   *
   * @param request Job update request body.
   */
  @Throws(MBlastJobNotFoundException::class, MBlastAPIException::class)
  fun patchJob(jobID: HashID, request: ReportJobPatchRequest)

  /**
   * Deletes a target job.
   *
   * If the target job does not exist, this method will throw an exception.
   *
   * @param jobID ID of the target job to delete.
   */
  @Throws(MBlastJobNotFoundException::class, MBlastAPIException::class)
  fun deleteJob(jobID: HashID)

  /**
   * Attempts to create a new report job.
   *
   * @param fn Request builder.
   *
   * @return An [Either] containing either a 422 error body as a JsonNode or a
   * job creation response on success.
   */
  @Throws(MBlastAPIException::class)
  fun createJob(fn: ReportJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse>

  /**
   * Returns the list of files available for download from a job.
   *
   * If the target job doesn't exist, `null` is returned.
   *
   * @param jobID ID of the job for which files should be listed.
   *
   * @return Either a list of available file entries, if the job exists, or
   * `null` if the job doesn't exist.
   */
  @Throws(MBlastAPIException::class)
  fun listJobFiles(jobID: HashID): List<FileEntry>?

  /**
   * Returns a stream over the contents of a target file attached to a target
   * job.
   *
   * If the target job or file does not exist, `null` will be returned.
   *
   * @param jobID ID of the job whose file should be returned.
   *
   * @param fileName Name of the file that should be returned.
   *
   * @return An input stream over the contents of the target file, if it exists,
   * otherwise `null`.
   */
  @Throws(MBlastAPIException::class)
  fun getJobFile(jobID: HashID, fileName: String): InputStream?

  /**
   * Returns a stream over the contents of the stderr output of a target job.
   *
   * If the target job does not exist, `null` will be returned.
   *
   * @param jobID ID of the job whose stderr output should be returned.
   *
   * @return An input stream over the contents of the target job's stderr
   * output, or `null` if the job doesn't exist.
   */
  @Throws(MBlastAPIException::class)
  fun getJobStdErr(jobID: HashID): InputStream?

  /**
   * Bulk job status lookup.
   *
   * Looks up the statuses of the jobs whose IDs are provided to this method.
   *
   * For each job that exists, the output will contain an entry with that job's
   * status.
   *
   * @param jobIDs IDs of jobs whose statuses will be checked.
   *
   * @return Job status response containing the statuses for valid job IDs that
   * appeared in the given list of IDs.
   */
  @Throws(MBlastAPIException::class)
  fun getBulkJobStatuses(jobIDs: Iterable<HashID>): JobBulkStatusResponse
}