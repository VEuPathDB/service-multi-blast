package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.query.generated.model.QueryJobPatchRequest
import org.veupathdb.service.mblast.query.generated.resources.JobsJobId
import org.veupathdb.service.mblast.query.generated.support.PATCH
import org.veupathdb.service.mblast.query.service.jobs.delete.DeleteUserJobLink
import org.veupathdb.service.mblast.query.service.jobs.fetch.GetAndLinkJob
import org.veupathdb.service.mblast.query.service.jobs.fetch.GetJob
import org.veupathdb.service.mblast.query.service.jobs.patch.PatchUserJob
import org.veupathdb.service.mblast.query.service.jobs.restart.RestartJob

@Authenticated(allowGuests = true)
class JobByIDController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobId {

  @GET
  @Produces("application/json")
  override fun getJobsByJobId(
    @PathParam("job-id") jobId: String,
    @QueryParam("save_job") @DefaultValue("true") saveJob: Boolean
  ): JobsJobId.GetJobsByJobIdResponse =
    JobsJobId.GetJobsByJobIdResponse
      .respond200WithApplicationJson(
        if (saveJob)
          GetAndLinkJob(jobId.toHashIDOr404(), userID)
        else
          GetJob(jobId.toHashIDOr404(), userID)
      )

  @POST
  @Produces("application/json")
  override fun postJobsByJobId(@PathParam("job-id") jobId: String): JobsJobId.PostJobsByJobIdResponse {
    RestartJob(jobId.toHashIDOr404())
    return JobsJobId.PostJobsByJobIdResponse.respond204()
  }

  @PATCH
  @Produces("application/json")
  @Consumes("application/json")
  override fun deleteJobsByJobId(@PathParam("job-id") jobId: String): JobsJobId.DeleteJobsByJobIdResponse {
    DeleteUserJobLink(jobId.toHashIDOr404(), userID)
    return JobsJobId.DeleteJobsByJobIdResponse.respond204()
  }

  @DELETE
  @Produces("application/json")
  override fun patchJobsByJobId(@PathParam("job-id") jobId: String, entity: QueryJobPatchRequest?): JobsJobId.PatchJobsByJobIdResponse {
    PatchUserJob(jobId.toHashIDOr404(), userID, entity ?: throw BadRequestException())
    return JobsJobId.PatchJobsByJobIdResponse.respond204()
  }
}