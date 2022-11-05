package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobBulkStatusResponse
import org.veupathdb.lib.mblast.sdk.report.model.*
import org.veupathdb.lib.mblast.sdk.util.Either

internal class MultiBlastReportServiceAPIImpl(config: MultiBlastClientConfig)
  : MultiBlastServiceClient(config)
  , MultiBlastReportServiceAPI
{
  // // //
  //
  //   API Path Builders
  //
  // // //

  private inline fun pathJobs() = resolvePath("/report/jobs")
  private inline fun pathJobs(queryJobID: HashID) = resolvePath("/report/jobs?query_job_id=$queryJobID")
  private inline fun pathJob(jobID: HashID) = resolvePath("/report/jobs/$jobID")
  private inline fun pathJob(jobID: HashID, save: Boolean) = resolvePath("/report/jobs/$jobID?save_job=$save")
  private inline fun pathJobFiles(jobID: HashID) = resolvePath("/report/jobs/$jobID/files")
  private inline fun pathJobFile(jobID: HashID, file: String) = resolvePath("/report/jobs/$jobID/files/$file")
  private inline fun pathJobStdErr(jobID: HashID) = resolvePath("/report/jobs/$jobID/stderr")
  private inline fun pathStatuses() = resolvePath("/report/statuses")

  // // //
  //
  //   Implementation Methods
  //
  // // //

  override fun listJobs(queryJobID: HashID?): List<ReportJobListEntry> =
    (if (queryJobID == null) pathJobs() else pathJobs(queryJobID))
      .getRequest()
      .submit()
      .listResponse()

  override fun getJob(jobID: HashID, saveJob: Boolean): ReportJobDetails? =
    pathJob(jobID, saveJob)
      .getRequest()
      .submit()
      .optionalResponse()

  override fun restartJob(jobID: HashID) =
    pathJob(jobID)
      .postRequest()
      .submit()
      .require204("Job could not be restarted due to job not found")

  override fun patchJob(jobID: HashID, request: ReportJobPatchRequest) =
    pathJob(jobID)
      .jsonPatchRequest(request)
      .submit()
      .require204("Job could not be patched due to job not found.")

  override fun deleteJob(jobID: HashID) =
    pathJob(jobID)
      .deleteRequest()
      .submit()
      .require204("Could not delete job due to job not found.")

  override fun createJob(fn: ReportJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse> {
    val body = ReportJobPostRequestBuilder().apply(fn).build()

    return pathJobs()
      .jsonPostRequest(body)
      .submit()
      .let {
        when (it.statusCode()) {
          200  -> Either(value = MultiBlast.JSON.readValue(it.body(), JobCreateResponse::class.java))
          422  -> Either(error = MultiBlast.JSON.readTree(it.body()))
          else -> it.throwUnexpectedResponse()
        }
      }
  }

  override fun listJobFiles(jobID: HashID): List<FileEntry>? =
    pathJobFiles(jobID)
      .getRequest()
      .submit()
      .let {
        when (it.statusCode()) {
          200  -> MultiBlast.JSON.readValue(it.body(), object : TypeReference<List<FileEntry>>() {})
          404  -> null
          else -> it.throwUnexpectedResponse()
        }
      }

  override fun getJobFile(jobID: HashID, fileName: String) =
    pathJobFile(jobID, fileName)
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
}