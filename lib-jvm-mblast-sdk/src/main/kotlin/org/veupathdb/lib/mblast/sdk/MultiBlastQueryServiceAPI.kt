package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.query.http.*
import org.veupathdb.lib.mblast.sdk.util.Either
import java.io.InputStream
import java.io.Reader

sealed interface MultiBlastQueryServiceAPI {
  fun listJobs(site: String? = null): List<QueryJobListEntry>

  fun getJob(jobID: HashID, saveJob: Boolean = false): QueryJobDetails?

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