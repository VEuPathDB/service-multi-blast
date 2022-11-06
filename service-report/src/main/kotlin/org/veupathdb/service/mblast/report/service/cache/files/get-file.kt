package org.veupathdb.service.mblast.report.service.cache.files

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.hash_id.HashID
import java.io.InputStream

fun GetJobFile(jobID: HashID, fileName: String): InputStream {
  val job = AsyncPlatform.getJob(jobID)
    ?: throw NotFoundException()

  if (!job.status.isFinished)
    throw ForbiddenException()

  return AsyncPlatform.getJobFiles(jobID)
    .find { it.name == fileName }
    ?.open()
    ?: throw NotFoundException()
}