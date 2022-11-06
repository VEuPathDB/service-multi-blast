package org.veupathdb.service.mblast.report.service.mblast

import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.logging.log4j.LogManager
import org.gusdb.fgputil.Tuples.TwoTuple
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.service.mblast.report.ServiceOptions
import java.io.InputStream
import java.lang.Exception
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object MBlastQuerySvc {
  private val client  = HttpClient.newHttpClient()
  private val baseURL = "http://${ServiceOptions.queryServiceHost}"

  private inline fun jobUrl(queryJobID: HashID) =
    "$baseURL/jobs/$queryJobID?saveJob=false"

  private inline fun jobReportUrl(queryJobID: HashID) =
    "$baseURL/jobs/$queryJobID/result"

  fun getQueryStatus(queryJobID: HashID, userToken: TwoTuple<String, String>): JobStatus {
    val res = client.send(
      HttpRequest.newBuilder(URI(jobUrl(queryJobID)))
        .header(userToken.first, userToken.second)
        .GET()
        .build(),
      HttpResponse.BodyHandlers.ofString()
    )

    when (res.statusCode()) {
      200  -> {}
      401  -> throw IllegalStateException("User token worked for the report service but not the query service.")
      404  -> throw QueryJobNotFoundException(queryJobID)
      500  -> throw IllegalStateException("Query service returned a 500")
      else -> throw IllegalStateException("Query service returned unexpected code ${res.statusCode()}")
    }

    val body = Json.parse<ObjectNode>(res.body())

    return JobStatus.fromString(body["status"].textValue())
  }

  fun getQueryResult(queryJobID: HashID, userToken: TwoTuple<String, String>): InputStream {
    val res = client.send(
      HttpRequest.newBuilder(URI(jobReportUrl(queryJobID)))
        .header(userToken.first, userToken.second)
        .GET()
        .build(),
      HttpResponse.BodyHandlers.ofInputStream()
    )

    if (res.statusCode() != 200) {
      res.body().close()

      when (res.statusCode()) {
        401  -> throw IllegalStateException("User token rejected when fetching query result.")
        403  -> throw IllegalStateException("Query job not in the correct state")
        404  -> throw QueryJobNotFoundException(queryJobID)
        500  -> throw IllegalStateException("Query service returned a 500")
        else -> throw IllegalStateException("Query service returned unexpected code ${res.statusCode()}")
      }
    }

    return res.body()
  }

  class QueryJobNotFoundException(jobID: HashID) : Exception("Query job $jobID not found.")
}