package org.veupathdb.service.mblast.report.service.cache.stderr

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.Const
import org.veupathdb.service.mblast.report.service.ReportPlatform
import java.io.InputStream


/**
 * # Get StdErr File for Target Job
 *
 * Returns an `InputStream` over the contents of the target job's stderr output
 * log file.
 *
 * @param reportJobID ID of the job whose stderr log file should be returned.
 *
 * @return A stream over the contents of the target job's stderr output log.
 */
fun GetStdErrForJob(reportJobID: HashID): InputStream {
  val jobInfo = ReportPlatform.getJob(reportJobID)
    ?: throw NotFoundException()

  if (!jobInfo.status.isFinished)
    throw ForbiddenException()

  return ReportPlatform.openJobFile(reportJobID, Const.STD_ERR_FILE_NAME)
    ?: throw IllegalStateException("Job $reportJobID had no stderr file.")
}