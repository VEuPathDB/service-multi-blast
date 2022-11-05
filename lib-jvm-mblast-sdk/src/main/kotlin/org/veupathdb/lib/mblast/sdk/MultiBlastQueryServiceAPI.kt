package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobBulkStatusResponse
import org.veupathdb.lib.mblast.sdk.query.model.*
import org.veupathdb.lib.mblast.sdk.util.Either
import java.io.InputStream
import java.io.Reader

sealed interface MultiBlastQueryServiceAPI {

  /**
   * Fetches the list of jobs associated with the configured client user from
   * the MultiBlast query service.
   *
   * The results may be optionally filtered to only results relating to the
   * specified [TargetSite].
   *
   * @param site Optional site value to filter results by.
   *
   * Defaults to `null`.
   *
   * @return A list of zero or more jobs associated with the configured client
   * user, optionally filtered by target site.
   */
  fun listJobs(site: TargetSite? = null): List<QueryJobListEntry>

  /**
   * Fetches the target job from the MultiBlast query service, if it exists.
   *
   * If the target job does not exist, `null` will be returned.
   *
   * @param jobID ID Of the job to fetch.
   *
   * @param saveJob Whether the target job should be automatically added to the
   * configured client user's job collection.
   *
   * Defaults to `false`.
   *
   * @return Fetched details about the target job, if the job exists, otherwise
   * `null`.
   */
  fun getJob(jobID: HashID, saveJob: Boolean = false): QueryJobDetails?

  /**
   * Attempts to restart a target job in the MultiBlast query service.
   *
   * If the target job does not exist, this method will throw an exception.
   *
   * @param jobID ID of the target job to restart.
   */
  fun restartJob(jobID: HashID)

  fun patchJob(jobID: HashID, patch: QueryJobPatchRequest)

  fun deleteJob(jobID: HashID)

  fun createJob(fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse>

  fun createJob(query: String, fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse>

  fun createJob(query: Reader, fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse>

  fun getJobQuery(jobID: HashID): InputStream?

  fun getJobResult(jobID: HashID): InputStream?

  fun getJobStdErr(jobID: HashID): InputStream?

  fun getBulkJobStatuses(jobIDs: Iterable<HashID>): JobBulkStatusResponse

  fun getBlastableTargets(): MultiBlastTargetIndex
}