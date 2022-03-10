package mb.lib.clean

import mb.lib.config.Config
import mb.lib.util.IntCounter
import mb.lib.workspace.Workspaces
import org.apache.logging.log4j.LogManager
import java.nio.file.Path
import java.time.OffsetDateTime

object JobCleanup: Runnable
{
  private val Log  = LogManager.getLogger(JobCleanup::class.java)

  override fun run() {
    try {
      Log.info("Starting job cleanup.")

      val ws = Workspaces.getExpiredWorkspaces()
      ws.forEach { it.delete() }

      Log.info("Job cleanup completed.  Deleted {} workspaces.", ws.size)

    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
