package mb.lib.data

import mb.lib.model.HashID
import org.apache.logging.log4j.LogManager
import java.nio.file.Files
import java.nio.file.Path
import java.time.OffsetDateTime

data class JobWorkspace(val jobID: HashID, val workspace: Path) {
  companion object {
    const val ResultFile = "report.asn1"
    const val ErrorFile  = "error.log"
  }

  private val log = LogManager.getLogger(JobWorkspace::class)

  val exists get() = workspace.toFile().exists()

  val resultPath get() = filePath(ResultFile)

  val resultExists get() = fileExists(ResultFile)

  val errorFilePath get() = filePath(ErrorFile)

  val errorFileExists get() = fileExists(ErrorFile)

  fun updateLastModified() = updateLastModified(OffsetDateTime.now())

  fun filePath(file: String): Path {
    log.trace("::filePath(file={})", file)
    return workspace.resolve(file).also { log.trace("::filePath() -> {}", it) }
  }

  fun fileExists(file: String): Boolean {
    log.trace("::fileExists(file={})", file)
    return filePath(file).toFile().exists().also { log.trace("::fileExists() -> {}", it) }
  }

  val errorText: String get() {
    val errorPath = errorFilePath
    val errorFile = errorPath.toFile()

    if (!errorFile.canRead())
      throw IllegalStateException("Cannot read job error log for job $jobID")

    try {
      return Files.readString(errorPath)
    } catch (e: Exception) {
      throw IllegalStateException("Failed to open job error log for job $jobID")
    }
  }

  /**
   * Generates a path to the workspace of the report identified by
   * {@code reportID}.
   * <p>
   * Note, this method does not check if the workspace actually exists, it
   * simply generates a path.
   *
   * @param reportID ID of the report for which a workspace path should be
   *                 generated.
   *
   * @return The generated report workspace path.
   */
  fun reportPath(reportID: HashID) = filePath(reportID.string)

  /**
   * Verifies that there exists a report workspace for the given report ID.
   *
   * @param reportID Report ID of the workspace to check for.
   *
   * @return {@code true} if a workspace exists for the given report ID,
   * otherwise {@code false}.
   */
  fun reportWorkspaceExists(reportID: HashID): Boolean {
    log.trace("::reportWorkspaceExists(reportID={})", reportID)
    return fileExists(reportID.string).also { log.trace("::reportWorkspaceExists() -> {}", it) }
  }

  fun reportWorkspace(reportID: HashID) = ReportWorkspace(jobID, reportID, reportPath(reportID))

  fun updateLastModified(time: OffsetDateTime) {
    workspace.toFile().setLastModified(time.toInstant().toEpochMilli())
  }
}
