package mb.lib.workspace

import mb.lib.config.Config
import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.time.Instant
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.stream.Collectors

internal object Workspaces
{
  val workspaceRoot by lazy { File(Config.jobMountPath) }

  fun getExpiredWorkspaces(): List<MBlastWorkspace> {
    val expirationThreshold = OffsetDateTime.now()
      .minus(Config.jobTimeout.toLong(), ChronoUnit.DAYS)
      .toInstant()

    if (!workspaceRoot.exists())
      return emptyList()

    if (!workspaceRoot.isDirectory)
      throw IllegalStateException()

    return Arrays.stream(workspaceRoot.listFiles())
      .filter { Instant.ofEpochMilli(it.lastModified()).isBefore(expirationThreshold) }
      .map { HashID(it.name) }
      .map(this::open)
      .collect(Collectors.toList())
  }

  fun open(jobID: HashID): UnresolvedWorkspace {
    return UnresolvedWorkspace(jobID, File(Config.jobMountPath, jobID.string))
  }
}
