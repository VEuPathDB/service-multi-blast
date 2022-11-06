package org.veupathdb.service.mblast.report.service.cache.files

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.service.ReportPlatform
import java.io.InputStream

fun GetJobFile(jobID: HashID, fileName: String): InputStream {
  val job = ReportPlatform.getJob(jobID)
    ?: throw NotFoundException()

  if (!job.status.isFinished)
    throw ForbiddenException()


  return ReportPlatform.openJobFile(jobID, fileName)
    ?: throw NotFoundException()
}