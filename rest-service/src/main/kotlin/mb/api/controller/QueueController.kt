package mb.api.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import mb.api.controller.resources.Paths
import mb.lib.http.MimeType
import mb.lib.query.BlastQueueManager
import mb.lib.query.model.BlastRequest
import mb.lib.report.ReportQueueManager
import mb.lib.util.jsonCast
import org.veupathdb.lib.jackson.Json
import java.util.stream.Collectors

@Path(Paths.Queues)
class QueueController {

  @GET
  @Path(Paths.BlastQueue)
  @Produces(value = [MimeType.ApplicationJSON])
  fun getBlastQueue(): List<String> {
    return BlastQueueManager.grabbedJobs()
      .stream()
      .map { it.payload }
      .map { Json.jsonCast<BlastRequest>() }
      .map { it.jobID }
      .map { it.string }
      .collect(Collectors.toList())
  }

  @GET
  @Path(Paths.ReportQueue)
  @Produces(value = [MimeType.ApplicationJSON])
  fun getReportQueue(): List<String> {
    return ReportQueueManager.grabbedJobs()
      .stream()
      .map { it.payload }
      .map { Json.jsonCast<BlastRequest>() }
      .map { it.jobID }
      .map { it.string }
      .collect(Collectors.toList())
  }
}