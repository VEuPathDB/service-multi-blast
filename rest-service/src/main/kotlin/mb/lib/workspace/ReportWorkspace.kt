package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File

sealed interface ReportWorkspace : Workspace {
  /**
   * Parent blast workspace.
   */
  val parent: BlastWorkspace

  /**
   * ID of this report workspace.
   */
  val reportID: HashID

  /**
   * Indicates whether this report workspace contains a result zip file.
   *
   * This does not indicate whether the job is completed as the file may be
   * being actively written to.
   *
   * The presence of this file _does_ indicate that the report job has at least
   * started.
   */
  val hasReportZip: Boolean

  /**
   * A handle on the result zip file.
   *
   * This handle does not indicate whether the result zip file actually exists
   * in the workspace.  This can be tested with [File.exists] or [hasReportZip].
   */
  val reportZip: File

  /**
   * Indicates whether this report workspace contains a meta json file.
   *
   * This does not indicate whether the job is completed as the file may be
   * being actively written to.
   *
   * The presence of this file _does_ indicate that the report job has at least
   * started.
   */
  val hasMetaJson: Boolean

  /**
   * A handle on the result zip file.
   *
   * This handle does not indicate whether the meta json file actually exists in
   * the workspace.  This can be tested with [File.exists] or [hasMetaJson].
   */
  val metaJson: File
}

internal class ReportWorkspaceImpl(
  override val parent: BlastWorkspace,
  override val reportID: HashID
)
  : AbstractWorkspace(File(parent.directory, reportID.string))
  , ReportWorkspace
{

  companion object {
    const val ReportZip = "report.zip"
    const val MetaJson  = "meta.json"
  }

  override val hasReportZip
    get() = reportZip.exists()

  override val hasMetaJson
    get() = metaJson.exists()

  override val reportZip by lazy { File(directory, ReportZip) }

  override val metaJson by lazy { File(directory, MetaJson) }
}