package mb.lib.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.OffsetDateTime;
import java.util.*;

import mb.lib.config.Config;
import mb.lib.model.HashID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobDataManager
{
  private static final Logger Log       = LogManager.getLogger(JobDataManager.class);
  private static final Path   JobRoot   = Path.of(Config.getInstance().getJobMountPath());
  private static final Path   DBRoot    = Path.of(Config.getInstance().getDbMountPath());
  private static final String Build     = "build-" + Config.getInstance().getBuildNum();
  private static final String BlastPath = "blast";

  /**
   * Creates a path string to the target blast DB.
   *
   * @param site Target DB site.
   * @param org  Target DB organism.
   * @param tgt  Target DB name.
   *
   * @return A path to the target DB file.
   */
  public static Path makeDBPath(String site, String org, String tgt) {
    Log.trace("::makeDBPath(site={}, org={}, tgt={})", site, org, tgt);
    return DBRoot.resolve(site).resolve(Build).resolve(org).resolve(BlastPath).resolve(tgt);
  }

  /**
   * Returns whether a DB exists at the target path.
   * <p>
   * Confirms that either a *.pin or *.nin file exists for the given target DB
   * path.
   *
   * @param tgt Target path.
   *
   * @return Whether a DB file exists for the target path.
   */
  public static boolean targetDBExists(Path tgt) {
    Log.trace("::targetDBExists(tgt={})", tgt);

    if (!tgt.getParent().toFile().exists())
      return false;

    var base = tgt.toString();

    // Check for n-index
    if (new File(base + ".nin").exists())
      return true;

    // Check for p-index
    return new File(base + ".pin").exists();
  }

  /**
   * Creates a workspace for the given job ID.
   *
   * @param jobID ID of the job for which a workspace should be created.
   *
   * @return The path to the newly created workspace.
   */
  public static JobWorkspace createJobWorkspace(HashID jobID) throws Exception {
    Log.trace("::createJobWorkspace(jobID={})", jobID);
    return new JobWorkspace(
      jobID,
      Files.createDirectories(Path.of(conf.getJobMountPath(), jobID.string()))
    );
  }

  /**
   * Returns a list of paths that were last modified before the given timestamp.
   *
   * @param time Timestamp threshold.
   *
   * @return A list of paths that were last modified before the given timestamp.
   */
  public static List<Path> getPathsModifiedBefore(OffsetDateTime time) throws Exception {
    // 50 ~= 10 jobs
    final var out  = new ArrayList<Path>(50);
    final var root = Path.of(Config.getInstance().getJobMountPath());

    Files.walkFileTree(root, new SimpleFileVisitor<>()
    {
      // Used to keep the directory entries _after_ the file entries per
      // directory.
      private final Set<Path> dirs = new HashSet<>();

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (attrs.lastModifiedTime().toInstant().isBefore(time.toInstant()))
          out.add(file);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        if (attrs.lastModifiedTime().toInstant().isBefore(time.toInstant()))
          if (!dir.equals(root))
            dirs.add(dir);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        if (dirs.contains(dir))
          out.add(dir);
        return FileVisitResult.CONTINUE;
      }
    });

    return out;
  }

  public static Path jobFilePath(String file) {
    return JobRoot.resolve(file);
  }

  public static boolean jobFileExists(String file) {
    return jobFilePath(file).toFile().exists();
  }

  public static Path workspacePath(HashID jobID) {
    return jobFilePath(jobID.string());
  }

  public static boolean workspaceExists(HashID jobID) {
    return jobFileExists(jobID.string());
  }

  public static JobWorkspace jobWorkspace(HashID jobID) {
    return new JobWorkspace(jobID, workspacePath(jobID));
  }

  /**
   * Wipe out a job directory and it's contents.
   *
   * @param jobID ID of the job whose directory should be erased.
   */
  public static void deleteWorkspace(HashID jobID) throws Exception {
    Log.trace("::deleteJobData(jobID={})", jobID);

    var path = Path.of(conf.getJobMountPath(), jobID.string());

    // If the path doesn't exist, nothing to do.
    if (!path.toFile().exists())
      return;

    Files.walk(path)
      .sorted(Comparator.reverseOrder())
      .map(Path::toFile)
      .forEach(File::delete);
  }
}
