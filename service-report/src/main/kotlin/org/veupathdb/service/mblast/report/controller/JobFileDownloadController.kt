package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.service.mblast.report.ext.toStreamingOutput
import org.veupathdb.service.mblast.report.generated.resources.JobsJobIdFilesFilename
import org.veupathdb.service.mblast.report.generated.resources.JobsJobIdFilesFilename.GetJobsFilesByJobIdAndFilenameResponse
import org.veupathdb.service.mblast.report.service.cache.files.GetJobFile

class JobFileDownloadController(@Context request: ContainerRequest) : ControllerBase(request), JobsJobIdFilesFilename {

  override fun getJobsFilesByJobIdAndFilename(
    jobId: String,
    filename: String,
    download: Boolean,
  ): GetJobsFilesByJobIdAndFilenameResponse {
    val headers = GetJobsFilesByJobIdAndFilenameResponse.headersFor200()

    if (download)
      headers.withContentDisposition("attachment; filename=\"$filename\"")

    return GetJobsFilesByJobIdAndFilenameResponse.respond200With(
      GetJobFile(jobId.toHashIDOr404(), filename).toStreamingOutput(),
      headers,
    )
  }
}