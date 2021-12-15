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
    get() = "/$root/$site/$build/$organism/$application/$target"

  override val exists: Boolean
    get() = with(fullPath) { File("$this.nin").exists() || File("$this.pin").exists() }
}

internal data class RawDBPath(override val fullPath: String) : DBPath {
  private val s1 = fullPath.indexOf('/', 1).toUByte()
  private val s2 = fullPath.indexOf('/', (s1 + 1u).toInt()).toUByte()
  private val s3 = fullPath.indexOf('/', (s2 + 1u).toInt()).toUByte()
  private val s4 = fullPath.indexOf('/', (s3 + 1u).toInt()).toUByte()
  private val s5 = fullPath.indexOf('/', (s4 + 1u).toInt()).toUByte()

  override val root
    get() = fullPath.substring(1, s1.toInt())

  override val site
    get() = fullPath.substring(s1.toInt()+1, s2.toInt())

  override val build
    get() = fullPath.substring(s2.toInt()+1, s3.toInt())

  override val organism
    get() = fullPath.substring(s3.toInt()+1, s4.toInt())

  override val application
    get() = fullPath.substring(s4.toInt()+1, s5.toInt())

  override val target
    get() = fullPath.substring(s5.toInt()+1)

  override val exists
    get() = when {
      File("$fullPath.nin").exists() -> true
      File("$fullPath.pin").exists() -> true
      else                           -> false
    }
}
