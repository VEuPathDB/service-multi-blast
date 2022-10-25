package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.query.generated.resources.JobsJobIdResult
import org.veupathdb.service.mblast.query.mixins.toStreamingOutput
import org.veupathdb.service.mblast.query.service.cache.GetJobResult

@Authenticated(allowGuests = true)
class JobResultDownloadController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobIdResult {

  override fun getJobsResultByJobId(jobId: String, download: Boolean): JobsJobIdResult.GetJobsResultByJobIdResponse {
    val headers = JobsJobIdResult.GetJobsResultByJobIdResponse
      .headersFor200()
      .let { if (download) it.withContentDisposition("attachment; filename=\"report.asn1\"") else it }

    return JobsJobIdResult.GetJobsResultByJobIdResponse
      .respond200WithTextPlain(GetJobResult(jobId.toHashIDOr404()).toStreamingOutput(), headers)
  }
}