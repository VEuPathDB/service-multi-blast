package mb.lib.clean;

import java.io.File;
import java.nio.file.Path;
import java.time.OffsetDateTime;

import mb.lib.config.Config;
import mb.lib.data.JobDataManager;
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

      var deleted = JobDataManager.getPathsModifiedBefore(cutoff)
        .stream()
        .map(Path::toFile)
        .peek(File::delete)
        .count();

      Log.info("Job cleanup completed.  Deleted {} files.", deleted);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
