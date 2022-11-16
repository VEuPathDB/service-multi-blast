package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.query.generated.model.QueryJobPatchRequest
import org.veupathdb.service.mblast.query.generated.resources.JobsJobId
import org.veupathdb.service.mblast.query.service.jobs.delete.DeleteUserJobLink
import org.veupathdb.service.mblast.query.service.jobs.fetch.GetAndLinkJob
import org.veupathdb.service.mblast.query.service.jobs.fetch.GetJob
import org.veupathdb.service.mblast.query.service.jobs.patch.PatchUserJob
import org.veupathdb.service.mblast.query.service.jobs.restart.RestartJob

@Authenticated(allowGuests = true)
class JobByIDController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobId {

  override fun getJobsByJobId(jobId: String, saveJob: Boolean): JobsJobId.GetJobsByJobIdResponse =
    JobsJobId.GetJobsByJobIdResponse
      .respond200WithApplicationJson(
        if (saveJob)
          GetAndLinkJob(jobId.toHashIDOr404(), userID)
        else
          GetJob(jobId.toHashIDOr404(), userID)
      )

  override fun postJobsByJobId(jobId: String): JobsJobId.PostJobsByJobIdResponse {
    RestartJob(jobId.toHashIDOr404())
    return JobsJobId.PostJobsByJobIdResponse.respond204()
  }

  override fun deleteJobsByJobId(jobId: String): JobsJobId.DeleteJobsByJobIdResponse {
    DeleteUserJobLink(jobId.toHashIDOr404(), userID)
    return JobsJobId.DeleteJobsByJobIdResponse.respond204()
  }

  override fun patchJobsByJobId(jobId: String, entity: QueryJobPatchRequest?): JobsJobId.PatchJobsByJobIdResponse {
    PatchUserJob(jobId.toHashIDOr404(), userID, entity ?: throw BadRequestException())
    return JobsJobId.PatchJobsByJobIdResponse.respond204()
  }
}