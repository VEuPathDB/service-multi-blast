package mb.api.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import mb.api.controller.resources.Paths
import mb.lib.http.MimeType
import mb.lib.query.BlastQueueManager
import mb.lib.query.DiamondQueueManager
import mb.lib.query.model.BlastServerRequest
import mb.lib.report.ReportQueueManager
import mb.lib.util.jsonCast
import org.veupathdb.lib.jackson.Json

@Path(Paths.Queues)
class QueueController {

  @GET
  @Path(Paths.BlastQueue)
  @Produces(value = [MimeType.ApplicationJSON])
  fun getBlastQueue(): List<String> {
    return (BlastQueueManager.grabbedJobs().asSequence() + DiamondQueueManager.grabbedJobs().asSequence())
      .map { it.payload }
      .map { Json.jsonCast<BlastServerRequest>() }
      .map { it.jobID }
      .map { it.string }
      .toList()
  }

  @GET
  @Path(Paths.ReportQueue)
  @Produces(value = [MimeType.ApplicationJSON])
  fun getReportQueue(): List<String> {
    return ReportQueueManager.grabbedJobs()
      .asSequence()
      .map { it.payload }
      .map { Json.jsonCast<BlastServerRequest>() }
      .map { it.jobID }
      .map { it.string }
      .toList()
  }
}
