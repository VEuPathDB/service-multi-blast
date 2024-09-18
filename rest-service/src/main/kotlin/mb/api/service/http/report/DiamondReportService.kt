package mb.api.service.http.report

import jakarta.ws.rs.NotFoundException
import mb.api.model.reports.ReportResponse
import mb.lib.model.JobStatus
import mb.lib.util.logger
import mb.lib.workspace.DiamondReportWorkspace
import mb.lib.workspace.DiamondWorkspace
import mb.lib.workspace.Workspaces
import org.veupathdb.lib.hash_id.HashID
import java.io.File

internal object DiamondReportService {
  private val logger = logger()

  fun getReportInfo(queryJobID: HashID): ReportResponse =
    with(Workspaces.open(queryJobID).resolveAsDiamond()) {
      ReportResponse(queryJobID, queryJobID, null, when {
        hasResult        -> JobStatus.Completed
        hasFailedFlag()  -> JobStatus.Errored
        hasSuccessFlag() -> throw IllegalStateException("job $queryJobID marked as completed successfully, but has no report")
        else             -> JobStatus.Queued   // TODO: is it worth the effort to determine the real query job status?
      }).also {
        if (it.status == JobStatus.Completed)
          it.files.add(DiamondWorkspace.ResultFile)
      }
    }

  fun getReportFile(queryJobID: HashID): File =
    Workspaces.open(queryJobID)
      .resolveAsDiamond()
      .getFile(DiamondWorkspace.ResultFile)
      .takeIf { it.exists() }
      ?: throw NotFoundException()

  fun getReportZip(queryJobID: HashID): File =
    Workspaces.open(queryJobID)
      .resolveAsDiamond()
      .reportWorkspace(queryJobID)
      .getFile(DiamondReportWorkspace.ReportZip)
      .takeIf { it.exists() }
      ?: throw NotFoundException()

  fun downloadReport(queryJobID: HashID, download: Boolean): ReportDownload =
    ReportDownload(DiamondWorkspace.ResultFile, download, getReportFile(queryJobID).inputStream().buffered())
}
