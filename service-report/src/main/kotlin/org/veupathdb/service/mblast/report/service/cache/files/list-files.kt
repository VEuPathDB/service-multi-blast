package org.veupathdb.service.mblast.report.service.cache.files

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.generated.model.FileEntry
import org.veupathdb.service.mblast.report.generated.model.FileEntryImpl

fun ListJobFiles(jobID: HashID): List<FileEntry> {
  val job = AsyncPlatform.getJob(jobID)
    ?: throw NotFoundException()

  if (!job.status.isFinished)
    throw ForbiddenException()

  return AsyncPlatform.getJobFiles(jobID)
    .stream()
    .filter {
      when (it.name) {
        "config.json" -> false
        "stderr.txt"  -> false
        else          -> true
      }
    }
    .map { FileEntryImpl().apply {
      name = it.name
      size = it.size
    } }
    .toList()
}
