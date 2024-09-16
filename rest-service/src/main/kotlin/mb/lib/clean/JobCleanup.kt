package mb.lib.clean

import mb.lib.workspace.Workspaces
import org.apache.logging.log4j.LogManager

object JobCleanup: Runnable
{
  private val Log  = LogManager.getLogger(JobCleanup::class.java)

  override fun run() {
    try {
      Log.info("Starting job cleanup.")

      var deleteCount = 0

      Workspaces.getExpiredWorkspaces()
        .forEach {
          it.delete()
          deleteCount++
        }

      Log.info("Job cleanup completed.  Deleted {} workspaces.", deleteCount)
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
