package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.service.mblast.query.generated.resources.JobsJobIdStderr
import org.veupathdb.service.mblast.query.mixins.toStreamingOutput
import org.veupathdb.service.mblast.query.service.cache.GetStdErrForJob

class JobsStdErrController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobIdStderr {
  override fun getJobsStderrByJobId(jobId: String, download: Boolean): JobsJobIdStderr.GetJobsStderrByJobIdResponse =
    JobsJobIdStderr.GetJobsStderrByJobIdResponse.respond200WithTextPlain(
      GetStdErrForJob(jobId.toHashIDOr404()).toStreamingOutput(),
      JobsJobIdStderr.GetJobsStderrByJobIdResponse.headersFor200().also {
        if (download)
          it.withContentDisposition("attachment; filename=\"$jobId-stderr.txt\"")
      }
    )
}