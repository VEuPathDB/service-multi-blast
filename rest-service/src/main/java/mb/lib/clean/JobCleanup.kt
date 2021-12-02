package mb.lib.clean

import mb.lib.config.Config
import mb.lib.data.JobDataManager
import mb.lib.util.IntCounter
import org.apache.logging.log4j.LogManager
import java.nio.file.Path
import java.time.OffsetDateTime

object JobCleanup: Runnable
{
  private val Log  = LogManager.getLogger(JobCleanup::class.java)

  override fun run() {
    try {
      Log.info("Starting job cleanup.")

      val cutoff = OffsetDateTime.now().minusDays(Config.jobTimeout.toLong())

      val success = IntCounter()
      val failure = IntCounter()

      JobDataManager.getPathsModifiedBefore(cutoff)
        .stream()
        .map(Path::toFile)
        .forEach { file ->
          val del = file.delete()

          Log.debug("Target file {} deleted: {}", file.name, del)

          if (del)
            success.increment()
          else
            failure.increment()
        }

      Log.info("Job cleanup completed.  Successfully deleted {} files.  Failed to delete {} files.",
        success.value, failure.value)

    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
