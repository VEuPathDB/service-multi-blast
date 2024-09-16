package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File

internal class ReportWorkspaceImpl(
  override val parent: MBlastWorkspace,
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
