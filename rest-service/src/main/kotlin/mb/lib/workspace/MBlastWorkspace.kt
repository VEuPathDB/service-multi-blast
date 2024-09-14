package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File

sealed interface MBlastWorkspace : Workspace {

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
