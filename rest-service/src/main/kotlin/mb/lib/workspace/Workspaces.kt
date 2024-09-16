package mb.lib.workspace

import mb.lib.config.Config
import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.time.Instant
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

internal object Workspaces
{
  val workspaceRoot by lazy { File(Config.jobMountPath) }

  fun getExpiredWorkspaces(): Sequence<MBlastWorkspace> {
    val expirationThreshold = OffsetDateTime.now()
      .minus(Config.jobTimeout.toLong(), ChronoUnit.DAYS)
      .toInstant()

    if (!workspaceRoot.exists())
      return emptySequence()

    if (!workspaceRoot.isDirectory)
      throw IllegalStateException()

    return workspaceRoot.listFiles()!!
      .asSequence()
      .filter { Instant.ofEpochMilli(it.lastModified()).isBefore(expirationThreshold) }
      .map { HashID(it.name) }
      .map { open(it).resolve() }
  }

  fun open(jobID: HashID): UnresolvedWorkspace {
    return UnresolvedWorkspace(jobID, File(Config.jobMountPath, jobID.string))
  }
}
