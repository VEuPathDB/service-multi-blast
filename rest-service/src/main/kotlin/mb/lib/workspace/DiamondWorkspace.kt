package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.io.InputStream

/**
 * Represents the working directory for a singular diamond job.
 */
internal class DiamondWorkspace(override val jobID: HashID, directory: File)
  : AbstractWorkspace(directory)
  , MBlastWorkspace
{
  companion object {
    const val ResultFile = "result.txt"
  }

  override val hasQuery     get() = queryFile.exists()
  override val hasResult    get() = resultFile.exists()
  override val hasReports   get() = hasResult
  override val hasErrorFile get() = errorFile.exists()
  override val hasLogFile   get() = logFile.exists()

  override val queryFile  by lazy { File(directory, QueryFile) }
  override val resultFile by lazy { File(directory, ResultFile) }
  override val errorFile  by lazy { File(directory, ErrorFile) }
  override val logFile    by lazy { File(directory, LogFile) }

  override val reports: List<HashID>
    get() = listOf(jobID)

  override fun createQueryFile(query: String) = createFile(QueryFile, query)

  override fun createQueryFile(query: InputStream) = createFile(QueryFile, query)

  override fun createIfNotExists(): Boolean {
    return if (super.createIfNotExists()) {
      File(directory, diamondFlagFile).createNewFile()
      true
    } else {
      false
    }
  }

  override fun reportWorkspace(reportID: HashID): ReportWorkspace {
    require(reportID == this.jobID) { "diamond jobs do not have distinct report ids" }
    return DiamondReportWorkspace(this)
  }
}

