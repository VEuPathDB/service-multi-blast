package mb.lib.path

import mb.lib.util.logger
import java.io.File

interface DBPath {
  val fullPath: String
  val root: String
  val site: String
  val build: String
  val organism: String?
  val application: String
  val target: String
  val exists: Boolean
}

private val IndexFileExtensions = arrayOf(
  "pin", // protein db index file
  "nin", // nucleotide db index file

  "pal", // protein db alias file
  "nal", // protein db alias file
)

internal data class SplitDBPath(
  override val root: String,
  override val site: String,
  override val build: String,
  override val organism: String?,
  override val application: String,
  override val target: String,
): DBPath {
  override val fullPath
    get() = if (organism.isNullOrBlank()) // OrthoMCL has no organism paths
      "/$root/$site/$build/$application/$target"
    else
      "/$root/$site/$build/$organism/genomeAndProteome/$application/$target"

  override val exists: Boolean
    get() {
      logger<DBPath>().debug("testing for an index or alias file file matching \"{}\"", this)
      return with(fullPath) { IndexFileExtensions.any {
        File("$this.$it")
          .also { logger<DBPath>().debug("testing for file {}", it) }
          .exists()
      } }
    }
}
