package org.veupathdb.service.mblast.report.service.cache.files

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.generated.model.FileEntry
import org.veupathdb.service.mblast.report.generated.model.FileEntryImpl

/**
 * Returns a list of result files present in the cache for a target report job.
 *
 * @param jobID ID of the target report job whose result files should be listed.
 *
 * @return A list of zero or more files present in the cache for the target job.
 *
 * @throws NotFoundException If the target job does not exist.
 *
 * @throws ForbiddenException If the target job is not in a finished state.
 */
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
