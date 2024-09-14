package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.util.Arrays
import java.util.stream.Collectors

/**
 * Represents the working directory for a singular blast job.
 */
internal class BlastWorkspace(override val jobID: HashID, directory: File)
  : AbstractWorkspace(directory)
  , MBlastWorkspace
{
  companion object {
    const val ResultFile = "result.asn1"
  }

  override val hasQuery get() = queryFile.exists()

  override val hasResult get() = resultFile.exists()

  override val hasReports get() = contents().anyMatch { it.isDirectory }

  override val hasErrorFile get() = errorFile.exists()

  override val hasLogFile get() = logFile.exists()

  override val queryFile by lazy { File(directory, QueryFile) }

  override val resultFile by lazy { File(directory, ResultFile) }

  override val errorFile by lazy { File(directory, ErrorFile) }

  override val logFile by lazy { File(directory, LogFile) }

  override val reports: List<HashID>
    get() = Arrays.stream(directory.listFiles())
      .filter { it.isDirectory }
      .map { it.name }
      .map(::HashID)
      .collect(Collectors.toList())

  override fun createQueryFile(query: String) = createFile(QueryFile, query)

  override fun reportWorkspace(reportID: HashID): ReportWorkspace =
    ReportWorkspaceImpl(this, reportID)
}
