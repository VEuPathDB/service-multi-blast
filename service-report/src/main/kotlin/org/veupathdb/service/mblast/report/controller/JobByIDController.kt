package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.AllowAdminAuth
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.report.generated.model.ReportJobPatchRequest
import org.veupathdb.service.mblast.report.generated.resources.JobsJobId
import org.veupathdb.service.mblast.report.service.jobs.delete.DeleteJob
import org.veupathdb.service.mblast.report.service.jobs.get.GetJob
import org.veupathdb.service.mblast.report.service.jobs.get.GetJobAdmin
import org.veupathdb.service.mblast.report.service.jobs.patch.PatchUserJob
import org.veupathdb.service.mblast.report.service.jobs.restart.RestartJob

@Authenticated(allowGuests = true)
class JobByIDController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobId {

  @AllowAdminAuth
  override fun getJobsByJobId(jobId: String, saveJob: Boolean): JobsJobId.GetJobsByJobIdResponse {
    if (optUserID == null)
      return JobsJobId.GetJobsByJobIdResponse.respond200WithApplicationJson(GetJobAdmin(jobId.toHashIDOr404()))

    return GetJob(jobId.toHashIDOr404(), userID, saveJob)
      ?.let { JobsJobId.GetJobsByJobIdResponse.respond200WithApplicationJson(it) }
      ?: throw NotFoundException()
  }

  override fun postJobsByJobId(jobId: String): JobsJobId.PostJobsByJobIdResponse {
    RestartJob(jobId.toHashIDOr404(), authHeader)
    return JobsJobId.PostJobsByJobIdResponse.respond204()
  }

  override fun patchJobsByJobId(jobId: String, entity: ReportJobPatchRequest?): JobsJobId.PatchJobsByJobIdResponse {
    PatchUserJob(jobId.toHashIDOr404(), userID, entity ?: throw BadRequestException())
    return JobsJobId.PatchJobsByJobIdResponse.respond204()
  }

  override fun deleteJobsByJobId(jobId: String): JobsJobId.DeleteJobsByJobIdResponse {
    DeleteJob(jobId.toHashIDOr404(), userID)
    return JobsJobId.DeleteJobsByJobIdResponse.respond204()
  }
}