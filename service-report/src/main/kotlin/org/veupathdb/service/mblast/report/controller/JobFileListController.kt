package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.service.mblast.report.generated.resources.JobsJobIdFiles
import org.veupathdb.service.mblast.report.service.cache.files.ListJobFiles

class JobFileListController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobIdFiles {
  override fun getJobsFilesByJobId(jobId: String): JobsJobIdFiles.GetJobsFilesByJobIdResponse =
    JobsJobIdFiles.GetJobsFilesByJobIdResponse.respond200WithApplicationJson(ListJobFiles(jobId.toHashIDOr404()))
}