package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.databind.JsonNode
import io.foxcapades.lib.k.multipart.MultiPart
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobBulkStatusResponse
import org.veupathdb.lib.mblast.sdk.query.model.*
import org.veupathdb.lib.mblast.sdk.util.Either
import java.io.InputStream
import java.io.Reader
import java.net.http.HttpResponse

internal class MultiBlastQueryServiceAPIImpl(config: MultiBlastClientConfig)
  : MultiBlastServiceClient(config)
  , MultiBlastQueryServiceAPI
{

  private inline fun pathJobs() = resolvePath("/query/jobs")
  private inline fun pathJobs(query: String) = resolvePath("/query/jobs?$query")
  private inline fun pathJob(jobID: HashID) = resolvePath("/query/jobs/$jobID")
  private inline fun pathJob(jobID: HashID, save: Boolean) = resolvePath("/query/jobs/$jobID?save_job=$save")
  private inline fun pathJobQuery(jobID: HashID) = resolvePath("/query/jobs/$jobID/query")
  private inline fun pathJobResult(jobID: HashID) = resolvePath("/query/jobs/$jobID/result")
  private inline fun pathJobStdErr(jobID: HashID) = resolvePath("/query/jobs/$jobID/stderr")
  private inline fun pathStatuses() = resolvePath("/query/statuses")
  private inline fun pathTargets() = resolvePath("/query/targets")


  override fun listJobs(site: TargetSite?): List<QueryJobListEntry> =
    (if (site == null) pathJobs() else pathJobs("site=$site"))
      .getRequest()
      .submit()
      .listResponse()

  override fun getJob(jobID: HashID, saveJob: Boolean): QueryJobDetails? =
    pathJob(jobID, saveJob)
      .getRequest()
      .submit()
      .optionalResponse()

  override fun restartJob(jobID: HashID) =
    pathJob(jobID)
      .postRequest()
      .submit()
      .require204("Job could not be restarted due to job not found.")

  override fun patchJob(jobID: HashID, patch: QueryJobPatchRequest) =
    pathJob(jobID)
      .jsonPatchRequest(patch)
      .submit()
      .require204("Job could not be patched due to job not found.")

  override fun deleteJob(jobID: HashID) =
    pathJob(jobID)
      .deleteRequest()
      .submit()
      .require204("Could not delete job due to job not found.")

  override fun getJobQuery(jobID: HashID) =
    pathJobQuery(jobID)
      .getRequest()
      .submit()
      .optionalStreamResponse()

  override fun getJobResult(jobID: HashID) =
    pathJobResult(jobID)
      .getRequest()
      .submit()
      .optionalStreamResponse()

  override fun getJobStdErr(jobID: HashID) =
    pathJobStdErr(jobID)
      .getRequest()
      .submit()
      .optionalStreamResponse()

  override fun getBulkJobStatuses(jobIDs: Iterable<HashID>): JobBulkStatusResponse {
    val body = MultiBlast.JSON.createArrayNode()
      .also { for (jobID in jobIDs) it.add(jobID.string) }

    return pathStatuses()
      .jsonPostRequest(body)
      .submit()
      .response()
  }

  override fun getBlastableTargets(): MultiBlastTargetIndex =
    pathTargets()
      .getRequest()
      .submit()
      .response()

  override fun createJob(fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse> {
    val body = QueryJobPostRequestBuilder().apply(fn).build()

    return pathJobs()
      .postRequest(MultiPart.createBody {
        withPart {
          fieldName = "config"
          withJSONBody(MultiBlast.JSON.writeValueAsBytes(body))
        }
      })
      .submit()
      .handleCreateResponse()
  }

  override fun createJob(query: String, fn: QueryJobPostRequestBuilder.() -> Unit) =
    createJob(query.reader(), fn)

  override fun createJob(query: Reader, fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse> {
    val body  = QueryJobPostRequestBuilder().apply(fn).build()

    return pathJobs()
      .postRequest(MultiPart.createBody {
        withPart {
          fieldName = "config"
          withJSONBody(MultiBlast.JSON.writeValueAsBytes(body))
        }
        withPart {
          fieldName = "query"
          withPlainTextBody(query)
        }
      })
      .submit()
      .handleCreateResponse()
  }

  private fun HttpResponse<InputStream>.handleCreateResponse() : Either<JsonNode, JobCreateResponse> =
    when (statusCode()) {
      200  -> Either(value = body().use { MultiBlast.JSON.readValue(it, JobCreateResponse::class.java) })
      422  -> Either(error = body().use { MultiBlast.JSON.readTree(it) })
      else -> throwUnexpectedResponse()
    }
}