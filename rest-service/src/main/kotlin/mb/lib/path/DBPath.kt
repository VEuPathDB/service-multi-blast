package mb.lib.path

import java.io.File

interface DBPath {
  val fullPath: String
  val root: String
  val site: String
  val build: String
  val organism: String
  val application: String
  val target: String
  val exists: Boolean
}

internal fun DBPath.withBuild(build: String): DBPath = SplitDBPath(root, site, build, organism, application, target)

internal data class SplitDBPath(
  override val root: String,
  override val site: String,
  override val build: String,
  override val organism: String,
  override val application: String,
  override val target: String,
): DBPath {

  override val fullPath
    get() = "/$root/$site/$build/$organism/genomeAndProteome/$application/$target"

  override val exists: Boolean
    get() = with(fullPath) { File("$this.nin").exists() || File("$this.pin").exists() }
}

data class RawDBPath(override val fullPath: String): DBPath {
  private val rootEndIndex     = fullPath.indexOf('/', 1)
  private val siteEndIndex     = fullPath.indexOf('/', rootEndIndex + 1)
  private val buildEndIndex    = fullPath.indexOf('/', siteEndIndex + 1)
  private val organismEndIndex = fullPath.indexOf('/', buildEndIndex + 1)

  // step 1: find next '/' after static text "genomeAndProteome"
  private val applicationRange = fullPath.indexOf('/', organismEndIndex + 1)
    // step 2: find next '/' after that
    .let { staticSegment -> staticSegment+1..<fullPath.indexOf('/', staticSegment+1) }

  override val root
    get() = fullPath.substring(1, rootEndIndex)

  override val site
    get() = fullPath.substring(rootEndIndex + 1, siteEndIndex)

  override val build
    get() = fullPath.substring(siteEndIndex + 1, buildEndIndex)

  override val organism
    get() = fullPath.substring(buildEndIndex + 1, organismEndIndex)

  override val application
    get() = fullPath.substring(applicationRange)

  override val target
    get() = fullPath.substring(applicationRange.last + 2)

  override val exists
    get() = when {
      File("$fullPath.nin").exists() -> true
      File("$fullPath.pin").exists() -> true
      else                           -> false
    }
}
