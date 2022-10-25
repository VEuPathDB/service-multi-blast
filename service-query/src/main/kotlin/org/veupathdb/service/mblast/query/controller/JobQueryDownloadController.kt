package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.query.generated.resources.JobsJobIdQuery
import org.veupathdb.service.mblast.query.mixins.toStreamingOutput
import org.veupathdb.service.mblast.query.service.cache.GetJobQuery

@Authenticated(allowGuests = true)
class JobQueryDownloadController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobIdQuery {

  override fun getJobsQueryByJobId(jobId: String, download: Boolean): JobsJobIdQuery.GetJobsQueryByJobIdResponse {
    val headers = JobsJobIdQuery.GetJobsQueryByJobIdResponse
      .headersFor200()
      .let { if (download) it.withContentDisposition("attachment; filename=\"query.txt\"") else it }

    return JobsJobIdQuery.GetJobsQueryByJobIdResponse
      .respond200WithTextPlain(GetJobQuery(jobId.toHashIDOr404()).toStreamingOutput(), headers)
  }
}