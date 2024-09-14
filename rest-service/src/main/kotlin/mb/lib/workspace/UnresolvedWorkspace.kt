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

      if (flagFile.exists())
        return DiamondWorkspace(jobID, directory)
      else
        return BlastWorkspace(jobID, directory)
    }

    TODO("auto-determine if it is diamond or blast; throw if dir not exists")
  }
}
