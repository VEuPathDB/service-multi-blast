package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File

internal class UnresolvedWorkspace(
  val jobID: HashID,
  private val directory: File
) {
  val exists
    get() = directory.exists()

  val hasQuery
    get() = File(directory, AbstractWorkspace.QueryFile).exists()

  val hasDiamondFlag
    get() = File(directory, diamondFlagFile).exists()

  fun resolveAsDiamond() = DiamondWorkspace(jobID, directory)

  fun resolveAsBlast() = BlastWorkspace(jobID, directory)

  fun createAsDiamond(): DiamondWorkspace {
    TODO("create directory, create .diamond flag")
  }

  fun createAsBlast(): BlastWorkspace {
    TODO("create directory")
  }

  fun resolve(): MBlastWorkspace {
    if (directory.exists()) {
      val flagFile = File(directory, diamondFlagFile)

      return if (flagFile.exists())
        DiamondWorkspace(jobID, directory)
      else
        BlastWorkspace(jobID, directory)
    }

    throw IllegalStateException("attempted to resolve a workspace that does not exist: $directory")
  }
}
