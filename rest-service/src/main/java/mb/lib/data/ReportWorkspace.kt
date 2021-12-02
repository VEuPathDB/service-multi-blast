package mb.lib.data

import mb.lib.model.HashID
import mb.lib.util.parseJSON
import java.io.FileInputStream
import java.io.InputStream
import java.nio.file.Path

data class ReportWorkspace(
  val jobID:     HashID,
  val reportID:  HashID,
  val workspace: Path,
) {
  companion object {
    private const val ReportZip = "report.zip"
    private const val MetaJson  = "meta.json"
  }

  // Lazily loaded by #getReportMeta()
  val meta: ReportMeta by lazy { FileInputStream(reportMetaPath.toFile()).parseJSON() }

  /**
   * Returns the path to the report zip file.
   * <p>
   * This method does not verify the target file exists, it simply returns the
   * path.
   *
   * @return Path to the report zip file.
   */
  val zipPath: Path get() = workspace.resolve(ReportZip)

  /**
   * Verifies that the report zip file exists.
   *
   * @return {@code true} if the report zip file exists, otherwise
   * {@code false}.
   */
  val zipFileExists get() = zipPath.toFile().exists()

  /**
   * Returns a raw byte stream of the report zip file.
   *
   * @return A byte stream of the report zip file.
   */
  val zipStream get() = FileInputStream(zipPath.toFile())

  val reportMetaPath: Path get() = workspace.resolve(MetaJson)

  val reportMetaExists get() = fileExists(MetaJson)

  fun getFilePath(file: String): Path = workspace.resolve(file)

  fun fileExists(file: String) = getFilePath(file).toFile().exists()

  fun getFileStream(file: String): InputStream = FileInputStream(getFilePath(file).toFile())
}
