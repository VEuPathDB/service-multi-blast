package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.service.mblast.report.ext.toStreamingOutput
import org.veupathdb.service.mblast.report.generated.resources.JobsJobIdStderr
import org.veupathdb.service.mblast.report.service.cache.stderr.GetStdErrForJob

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