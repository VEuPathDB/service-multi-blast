package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.generated.model.ReportJobPostRequest
import org.veupathdb.service.mblast.report.generated.resources.Jobs
import org.veupathdb.service.mblast.report.service.jobs.list.GetJobs
import org.veupathdb.service.mblast.report.service.jobs.submit.SubmitJob

@Authenticated(allowGuests = true)
class JobsController(@Context request: ContainerRequest) : ControllerBase(request), Jobs {

  override fun getJobs(queryJobId: String?): Jobs.GetJobsResponse {
    if (queryJobId == null)
      return Jobs.GetJobsResponse.respond200WithApplicationJson(GetJobs(userID))

    val hashID = try {
      HashID(queryJobId)
    } catch (e: Throwable) {
      return Jobs.GetJobsResponse.respond200WithApplicationJson(emptyList())
    }

    return Jobs.GetJobsResponse.respond200WithApplicationJson(GetJobs(hashID, userID))
  }

  override fun postJobs(entity: ReportJobPostRequest?): Jobs.PostJobsResponse {
    if (entity == null)
      throw BadRequestException()

    return Jobs.PostJobsResponse.respond200WithApplicationJson(SubmitJob(entity, userID, authHeader))
  }
}