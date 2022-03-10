package mb.lib.workspace

import mb.lib.config.Config
import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.util.*
import java.util.stream.Collectors

sealed interface BlastWorkspace : Workspace {

  /**
   * ID of this blast workspace.
   */
  val jobID: HashID

  /**
   * Indicates whether this blast workspace contains a query file.
   */
  val hasQuery: Boolean

  /**
   * A handle on the query file in this workspace.
   *
   * This handle does not indicate whether the query file actually exists in the
   * workspace.  This can be tested with [File.exists] or [hasQuery].
   */
  val queryFile: File

  /**
   * Indicates whether this blast workspace contains a result file.
   *
   * This does not indicate whether the job is completed as the file may be
   * being actively written to.
   *
   * The presence of this file _does_ indicate that the blast job has at least
   * started.
   */
  val hasResult: Boolean

  /**
   * A handle on the result file in this workspace.
   *
   * This handle does not indicate whether the result file actually exists in
   * the workspace.  This can be tested with [File.exists] or [hasResult].
   */
  val resultFile: File

  /**
   * Indicates whether this blast workspace contains report workspaces.
   *
   * This does not indicate whether the reports represented by those report
   * workspaces are completed.
   *
   * The presence of reports _does_ indicate that the blast job has completed
   * successfully.
   */
  val hasReports: Boolean

  /**
   * A list of all the IDs of report workspaces contained in this blast
   * workspace.
   *
   * This does not indicate whether the reports represented by the returned IDs
   * are completed.
   *
   * The presence of reports _does_ indicate that the blast job has completed
   * successfully.
   */
  val reports: List<HashID>

  /**
   * Indicates whether the blast workspace contains an error log file.
   *
   * The presence of an error log file does not indicate whether the blast job
   * failed or completed.  The error log file is always created and may be being
   * actively written to if the blast job is still running.
   *
   * The presence of this file _does_ indicate that the blast job has at least
   * started.
   */
  val hasErrorFile: Boolean

  /**
   * A handle on the error log file in this workspace.
   *
   * This handle does not indicate whether the error file actually exists in
   * the workspace.  This can be tested with [File.exists] or [hasErrorFile].
   */
  val errorFile: File

  /**
   * Indicates whether the blast workspace contains a stdout log file.
   *
   * The presence of a stdout log file does not indicate whether the blast job
   * completed.  The stdout log is always created and may be being actively
   * written to if the blast job is still running.
   *
   * The presence of this file _does_ indicate that the blast job has at least
   * started.
   */
  val hasLogFile: Boolean

  /**
   * A handle on the stdout log file in this workspace.
   *
   * This handle does not indicate whether the stdout file actually exists in
   * the workspace.  This can be tested with [File.exists] or [hasLogFile].
   */
  val logFile: File

  /**
   * Create a query file with the given [query] text in this blast workspace.
   *
   * This method will fail if a query file already exists in this workspace.
   *
   * @param query Query text to write to the new query file.
   *
   * @return A handle on the new query file.
   */
  fun createQueryFile(query: String): File

  /**
   * Returns a [ReportWorkspace] instance wrapping the report workspace with the
   * given [reportID].
   *
   * This method will always return a `ReportWorkspace` instance regardless of
   * whether that workspace actually exists.
   *
   * The existence of the workspace can be tested with [ReportWorkspace.exists].
   */
  fun reportWorkspace(reportID: HashID): ReportWorkspace
}

/**
 * Represents the working directory for a singular blast job.
 */
internal class BlastWorkspaceImpl(override val jobID: HashID)
  : AbstractWorkspace(File(Config.jobMountPath, jobID.string))
  , BlastWorkspace
{

  companion object {
    const val QueryFile  = "query.txt"
    const val ResultFile = "result.asn1"
    const val ErrorFile  = "error.txt"
    const val LogFile    = "log.txt"
  }

  override val appearsComplete get() = hasQuery && hasResult

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

  override fun createQueryFile(content: String) = createFile(QueryFile, content)

  override fun reportWorkspace(reportID: HashID): ReportWorkspace =
    ReportWorkspaceImpl(this, reportID)
}