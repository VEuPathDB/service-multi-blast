package mb.lib.clean

import mb.lib.workspace.Workspaces
import org.apache.logging.log4j.LogManager

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
