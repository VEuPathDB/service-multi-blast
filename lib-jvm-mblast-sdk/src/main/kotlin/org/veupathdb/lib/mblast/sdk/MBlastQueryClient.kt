package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.query.http.*
import org.veupathdb.lib.mblast.sdk.util.Either
import java.io.InputStream
import java.io.Reader
import java.net.HttpURLConnection

internal class MBlastQueryClient(config: MultiBlastClientConfig)
  : MultiBlastServiceClient(config)
  , MultiBlastQueryServiceAPI
{

  private inline fun pathJobs() = resolvePath("/jobs")
  private inline fun pathJob(jobID: HashID) = resolvePath("/jobs/$jobID")
  private inline fun pathJob(jobID: HashID, save: Boolean) = resolvePath("/jobs/$jobID?saveJob=$save")
  private inline fun pathJobs(query: String) = resolvePath("/jobs?$query")
  private inline fun pathJobQuery(jobID: HashID) = resolvePath("/jobs/$jobID/query")
  private inline fun pathJobResult(jobID: HashID) = resolvePath("/jobs/$jobID/result")
  private inline fun pathJobStdErr(jobID: HashID) = resolvePath("/jobs/$jobID/stderr")
  private inline fun pathStatuses() = resolvePath("/statuses")
  private inline fun pathTargets() = resolvePath("/targets")


  override fun listJobs(site: String?): List<QueryJobListEntry> {
    val con = (if (site == null) pathJobs() else pathJobs("site=$site"))
      .openGETWithBody()

    return when (con.responseCode) {
      200  -> MultiBlast.JSON.readValue(con.inputStream, object : TypeReference<List<QueryJobListEntry>>() {})
      else -> con.throwUnexpectedResponse()
    }
  }

  override fun getJob(jobID: HashID, saveJob: Boolean): QueryJobDetails? {
    val con = pathJob(jobID, saveJob).openGETWithBody()

    return when (con.responseCode) {
      404  -> null
      200  -> MultiBlast.JSON.readValue(con.inputStream, QueryJobDetails::class.java)
      else -> throw IllegalStateException("Get job endpoint for job $jobID responded with status code ${con.responseCode}")
    }
  }

  override fun createJob(fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse> {
    val body  = QueryJobPostRequestBuilder().apply(fn).build()
    val bound = newBoundary()
    val con   = getJobsPostCon(bound)

    con.outputStream.bufferedWriter().use { w ->
      w.writeJsonPart(bound, "config", body)
      w.endTransmission(bound)
      w.flush()
    }

    return con.handleCreateResponse()
  }

  override fun createJob(query: String, fn: QueryJobPostRequestBuilder.() -> Unit) =
    createJob(query.reader(), fn)

  override fun createJob(query: Reader, fn: QueryJobPostRequestBuilder.() -> Unit): Either<JsonNode, JobCreateResponse> {
    val body  = QueryJobPostRequestBuilder().apply(fn).build()
    val bound = newBoundary()
    val con   = getJobsPostCon(bound)

    con.outputStream.bufferedWriter().use { w ->
      w.writeJsonPart(bound, "config", body)
      w.writeStringPart(bound, "query", query)
      w.endTransmission(bound)
      w.flush()
    }

    return con.handleCreateResponse()
  }

  override fun deleteJob(jobID: HashID) =
    pathJob(jobID)
      .openSimpleDELETE()
      .let {
        when (it.responseCode) {
          204  -> {}
          else -> it.throwUnexpectedResponse()
        }
      }


  override fun getJobQuery(jobID: HashID) =
    pathJobQuery(jobID).openGETWithBody().handleSimpleStreamResponse()


  override fun getJobResult(jobID: HashID) =
    pathJobResult(jobID).openGETWithBody().handleSimpleStreamResponse()


  override fun getJobStdErr(jobID: HashID) =
    pathJobStdErr(jobID).openGETWithBody().handleSimpleStreamResponse()


  override fun restartJob(jobID: HashID) =
    pathJob(jobID)
      .openSimplePOST()
      .let {
        when (it.responseCode) {
          204  -> {}
          404  -> throw IllegalStateException("Job could not be restarted due to job not found.")
          else -> it.throwUnexpectedResponse()
        }
      }


  override fun patchJob(jobID: HashID, patch: QueryJobPatchRequest) =
    pathJob(jobID)
      .openSimplePATCH()
      .apply { doOutput = true }
      .apply { setRequestProperty("Content-Type", "application/json") }
      .also { MultiBlast.JSON.writeValue(it.outputStream, patch) }
      .let {
        when (it.responseCode) {
          204  -> {}
          404  -> throw IllegalStateException("Job could not be patched due to job not found.")
          else -> it.throwUnexpectedResponse()
        }
      }


  override fun getBulkJobStatuses(jobIDs: Iterable<HashID>): JobBulkStatusResponse {
    val body = MultiBlast.JSON.createArrayNode()
      .also { for (jobID in jobIDs) it.add(jobID.string) }

    return pathStatuses().openPOSTWithIO("application/json")
      .also { MultiBlast.JSON.writeValue(it.outputStream, body) }
      .let {
        when (it.responseCode) {
          200  -> MultiBlast.JSON.readValue(it.inputStream, JobBulkStatusResponse::class.java)
          else -> it.throwUnexpectedResponse()
        }
      }
  }

  override fun getBlastableTargets(): MultiBlastTargetIndex =
    pathTargets()
      .openGETWithBody()
      .let {
        when (it.responseCode) {
          200  -> MultiBlast.JSON.readValue(it.inputStream, MultiBlastTargetIndex::class.java)
          else -> it.throwUnexpectedResponse()
        }
      }

  private fun HttpURLConnection.handleCreateResponse() : Either<JsonNode, JobCreateResponse> =
    when (responseCode) {
      200  -> Either(value = inputStream.use { MultiBlast.JSON.readValue(it, JobCreateResponse::class.java) })
      422  -> Either(error = errorStream.use { MultiBlast.JSON.readTree(it) })
      else -> throwUnexpectedResponse()
    }

  private fun HttpURLConnection.handleSimpleStreamResponse(): InputStream? =
    when (responseCode) {
      200  -> inputStream
      404  -> null
      else -> throwUnexpectedResponse()
    }

  private fun getJobsPostCon(boundary: String) =
    pathJobs().openPOSTWithIO("multipart/form-data;boundary=$boundary")
}