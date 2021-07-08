package mb.lib.clean;

import java.nio.file.Path;
import java.time.OffsetDateTime;

import mb.lib.config.Config;
import mb.lib.data.JobDataManager;
import mb.lib.util.IntCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobCleanup implements Runnable
{
  private static final Config Conf = Config.getInstance();
  private static final Logger Log = LogManager.getLogger(JobCleanup.class);

  @Override
  public void run() {
    try {
      Log.info("Starting job cleanup.");

      var cutoff = OffsetDateTime.now().minusDays(Conf.getJobTimeout());

      var success = new IntCounter();
      var failure = new IntCounter();

      JobDataManager.getPathsModifiedBefore(cutoff)
        .stream()
        .map(Path::toFile)
        .forEach(file -> {
          var del = file.delete();

          Log.debug("Target file {} deleted: {}", file.getName(), del);

          if (del)
            success.increment();
          else
            failure.increment();
        });

      Log.info("Job cleanup completed.  Successfully deleted {} files.  Failed to delete {} files.",
        success.get(), failure.get());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
