package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.query.generated.model.JobsPostMultipartFormData
import org.veupathdb.service.mblast.query.generated.resources.Jobs
import org.veupathdb.service.mblast.query.service.jobs.list.ListUserJobs
import org.veupathdb.service.mblast.query.service.jobs.submit.SubmitJob

@Authenticated(allowGuests = true)
class JobsController(@Context request: ContainerRequest) : ControllerBase(request), Jobs {

  override fun getJobs(site: String?) =
    Jobs.GetJobsResponse.respond200WithApplicationJson(ListUserJobs(userID, site))

  override fun postJobs(entity: JobsPostMultipartFormData) =
    SubmitJob(entity, userID)
      .let { Jobs.PostJobsResponse.respond200WithApplicationJson(it) }
}