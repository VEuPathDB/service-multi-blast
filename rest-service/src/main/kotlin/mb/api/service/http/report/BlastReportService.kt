package mb.api.service.http.report

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.NotFoundException
import mb.api.model.ContentRange
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.api.model.reports.toExternal
import mb.api.service.http.wrap
import mb.api.service.util.RangedStream
import mb.lib.model.JobStatus
import mb.lib.query.BlastManager
import mb.lib.report.ReportManager
import mb.lib.report.model.ReportJob
import mb.lib.report.model.UserReportRow
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.hash_id.HashID

internal object BlastReportService {
  private val Log = LogManager.getLogger(BlastReportService::class.java)

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
        ?: throw NotFoundException()

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
      // Verify blast job's existence and status.
      val job = BlastManager.getAndLinkUserBlastJob(req.jobID, userID) ?: throw NotFoundException()

      // If the blast job is not completed, then we can't run a report against
      // it.
      if (job.status != JobStatus.Completed)
        throw BadRequestException()

      // The blast job is completed, so we can submit a new report job.
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
      return ReportManager.rerunJob(reportID, userID)?.convert()
        ?: throw NotFoundException()

    } catch (e: Exception) {
      throw e.wrap()
    }
  }


  fun downloadReport(
    reportID: HashID,
    userID: Long,
    file: String,
    download: Boolean,
    maxSize: Long?,
    range: ContentRange?,
  ): ReportDownload {
    try {
      val rep = ReportManager.getAndLinkReport(reportID, userID)
        ?: throw NotFoundException()

      val out = ReportManager.getReportFile(rep, file) ?: throw NotFoundException()

      if (maxSize != null && out.length() > maxSize)
        throw BadRequestException("Requested report is larger than the max size specified by the Content-Max-Length request header.")

      if (rep.config?.outFormat == null)
        return ReportDownload(file, download, RangedStream(range, out.inputStream()))
      if (rep.config.outFormat!!.type == null)
        return ReportDownload(file, download, RangedStream(range, out.inputStream()))

      return ReportDownload(file, download, RangedStream(range, out.inputStream()))
    } catch (e: Exception) {
      throw e.wrap()
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  fun UserReportRow.convert() = ReportResponse(
    jobID,
    reportID,
    config?.toExternal(jobID),
    status,
    description
  )
}
