package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.lib.jackson.toJsonArray
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

internal class DiamondReportWorkspace(
  override val parent: MBlastWorkspace,
)
  : ReportWorkspace
  , Workspace by parent
{
  companion object {
    const val ReportZip = "report.zip"
    const val MetaJson  = "meta.json"
  }

  override val reportID: HashID
    get() = parent.jobID

  override val hasReportZip
    get() = parent.hasResult

  override val hasMetaJson
    get()  = parent.hasResult

  override val reportZip by lazy { ensureZip() }

  override val metaJson: File
    get() = TODO("Not yet implemented")

  private fun ensureZip(): File {
    val file = File(directory, ReportZip)

    if (file.exists())
      return file

    file.outputStream().buffered().use { bos ->
      ZipOutputStream(bos).use { zos ->
        val report = parent.resultFile

        zos.putNextEntry(ZipEntry(report.name))
        report.inputStream().use { it.copyTo(zos) }
      }
    }

    return file
  }

  private fun ensureMetaJson(): File {
    val file = File(directory, MetaJson)

    if (file.exists())
      return file

    file.outputStream().use { os ->
      Json.Mapper.writeValue(os, Json.newObject {
        put("files", listOf(DiamondWorkspace.ResultFile).toJsonArray())
      })
    }

    return file
  }
}
