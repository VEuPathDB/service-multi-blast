package mb.api.service.http.report

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.NotFoundException
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.api.model.reports.toExternal
import mb.api.service.http.wrap
import mb.lib.model.HashID
import mb.lib.model.JobStatus
import mb.lib.query.BlastManager
import mb.lib.report.ReportManager
import mb.lib.report.model.ReportJob
import mb.lib.report.model.UserReportRow
import org.apache.logging.log4j.LogManager

internal object ReportService {
  private val Log = LogManager.getLogger(ReportService::class.java)

  /**
   * Retrieves a list of all the report jobs created for the given {@code jobID}
   * value.
   * <p>
   * On report job status retrieval, the status will be automatically updated if
   * needed.
   *
   * @return A list of all the report jobs created for the given {@code jobID}.
   */
  fun listReports(jobID: HashID, userID: Long): List<ReportResponse> {
    Log.trace("::listReports(jobID={}, userID={})", jobID, userID)

    try {
      return ReportManager.getUserReportsForJob(jobID, userID).map { it.convert() }
    } catch (e: Exception) {
      throw e.wrap()
    }
  }

  /**
   * Retrieves a list of all the report jobs created by the user identified by
   * {@code userID}.
   *
   * @param userID ID of the user whose report jobs should be retrieved.
   *
   * @return A list of 0 or more report jobs linked to the user identified by
   * {@code userID}.
   */
  fun listAllReports(userID: Long): List<ReportResponse> {
    Log.trace("::listAllReports(userID={})", userID)

    try {
      return ReportManager.getAllReportsForUser(userID).map { it.convert() }
    } catch (e: Exception) {
      throw e.wrap()
    }
  }

  /**
   * Retrieves a report job identified by {@code reportID} for the user
   * identified by {@code userID}.
   * <p>
   * If the report job exists, but the user was not already linked to it, the
   * user will be linked and the matching job will be returned.
   *
   * @param reportID ID of the report to fetch.
   * @param userID   ID of the user who owns or will be linked to the report.
   *
   * @return The matching report.
   */
  fun getReport(reportID: HashID, userID: Long): ReportResponse {
    Log.trace("::getReport(reportID={}, userID={})", reportID, userID)

    try {
      val tmp = ReportManager.getAndLinkReport(reportID, userID)
        .orElseThrow(::NotFoundException)

      val out = tmp.convert()

      out.files.addAll(ReportManager.getReportFiles(tmp))

      return out
    } catch (e: Exception) {
      throw e.wrap()
    }
  }

  fun submitReport(req: ReportRequest, userID: Long): ReportResponse {
    Log.trace("::submitReport(req={}, userID={})", req, userID)

    try {
      // Verify job's existence and status.
      val job = BlastManager.getAndLinkUserBlastJob(req.jobID, userID) ?: throw NotFoundException()

      if (job.status != JobStatus.Completed)
        throw BadRequestException()

      return ReportManager.newReportJob(ReportJob(
        req.jobID,
        userID,
        req.toInternal(),
        req.description
      )).convert()
    } catch (e: Exception) {
      throw e.wrap()
    }
  }


  fun rerunReport(reportID: HashID, userID: Long): ReportResponse {
    Log.trace("::rerunReport(reportID={}, userID={})", reportID, userID)
    try {
      return ReportManager.rerunJob(reportID, userID)
        .map { it.convert() }
        .orElseThrow(::NotFoundException)
    } catch (e: Exception) {
      throw e.wrap()
    }
  }


  fun downloadReport(reportID: HashID, userID: Long, file: String, download: Boolean, maxSize: Long?): ReportDownload {
    Log.trace("::downloadReport(reportID={}, userID={}, file={}, download={}, maxSize={})", reportID, userID, file, download, maxSize)
    try {
      val rep = ReportManager.getAndLinkReport(reportID, userID)
        .orElseThrow(::NotFoundException)

      val out = ReportManager.getReportFile(rep, file) ?: throw NotFoundException()

      if (maxSize != null && out.length() > maxSize)
        throw BadRequestException("Requested report is larger than the specified max content size.")

      if (rep.config.outFormat == null)
        return ReportDownload(file, download, out.inputStream())
      if (rep.config.outFormat!!.type == null)
        return ReportDownload(file, download, out.inputStream())

      return ReportDownload(file, download, out.inputStream())
    } catch (e: Exception) {
      throw e.wrap()
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  fun UserReportRow.convert() = ReportResponse(
    jobID,
    reportID,
    config.toExternal(jobID),
    status,
    description
  )
}
