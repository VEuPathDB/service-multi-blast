package mb.lib.jobData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import mb.lib.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobDataManager
{
  private static final Logger log = LogManager.getLogger(JobDataManager.class);

  private static final Config conf         = Config.getInstance();
  private static final String jobErrorFile = "error.log";

  public static Path makeDBPath(String site, String org, String tgt) {
    log.trace("::makeDBPath(site={}, org={}, tgt={})", site, org, tgt);
    return Paths.get(conf.getDbMountPath(), site, "build-" + conf.getBuildNum(), org, "blast", tgt);
  }

  public static boolean reportExists(String jobID) {
    log.trace("::reportExists(jobID={})", jobID);
    return Path.of(conf.getJobMountPath(), jobID, "report.asn1").toFile().exists();
  }

  public static boolean targetDBExists(Path tgt) {
    log.trace("::targetDBExists(tgt={})", tgt);

    if (!tgt.getParent().toFile().exists())
      return false;

    var base = tgt.toString();

    // Check for n-index
    if (new File(base + ".nin").exists())
      return true;

    // Check for p-index
    return new File(base + ".pin").exists();
  }

  public static boolean jobDataExists(String jobID) {
    log.trace("::jobDataExists(jobID={})", jobID);

    var jobDir = Path.of(conf.getJobMountPath(), jobID).toFile();

    if (!jobDir.exists())
      return false;

    if (!jobDir.isDirectory())
      throw new IllegalStateException("Job path exists but is not a directory for job " + jobID);

    return true;
  }

  public static Path createJobWorkspace(String jobID) throws Exception {
    log.trace("::createJobWorkspace(jobID={})", jobID);
    return Files.createDirectories(Path.of(conf.getJobMountPath(), jobID));
  }

  public static File getJobQuery(String jobID) {
    log.trace("::getJobQuery(jobID={})", jobID);
    return getJobFile(jobID, "query.txt");
  }

  public static File getJobFile(String jobID, String fileName) {
    log.trace("::getJobFile(jobID={}, fileName={}", jobID, fileName);
    return Path.of(conf.getJobMountPath(), jobID, fileName).toFile();
  }

  public static File getJobFile(String jobID, File file) {
    return getJobFile(jobID, file.getName());
  }

  /**
   * Retrieves an optional stream of the contents of a job's error log.
   * <p>
   * If the job has no error.log file (the job did not error) then the returned
   * option is empty.
   *
   * @param jobID ID of the job to retrieve the error log for.
   *
   * @return An option that may contain a stream over the job's error log
   * contents.
   */
  public static Optional<String> getJobError(String jobID) {
    var errorPath = Path.of(conf.getJobMountPath(), jobID, jobErrorFile);
    var errorFile = errorPath.toFile();

    if (!errorFile.exists())
      return Optional.empty();
    if (!errorFile.canRead())
      throw new IllegalStateException("Cannot read job error log for job " + jobID);

    try {
      return Optional.of(Files.readString(errorPath));
    } catch (Exception e) {
      throw new IllegalStateException("Failed to open job error log for job " + jobID);
    }
  }

  /**
   * Wipe out a job directory and it's contents.
   *
   * @param jobID ID of the job whose directory should be erased.
   */
  public static void deleteJobData(String jobID) throws Exception {
    log.trace("::deleteJobData(jobID={})", jobID);

    var path = Path.of(conf.getJobMountPath(), jobID);

    // If the path doesn't exist, nothing to do.
    if (!path.toFile().exists())
      return;

    Files.walk(path)
      .sorted(Comparator.reverseOrder())
      .map(Path::toFile)
      .forEach(File::delete);
  }

  public static Collection<File> getJobUserFiles(String jobID) {
    log.trace("::getJobUserFiles(jobID={})", jobID);

    return getAllJobFiles(jobID).stream()
      .filter(file -> !file.getName().equals(jobErrorFile))
      .collect(Collectors.toList());
  }

  /**
   * Returns all files found in the job directory for the given job ID.
   *
   * @param jobID ID of the job to lookup files for.
   *
   * @return A list of all files associated with that job.
   */
  public static Collection<File> getAllJobFiles(String jobID) {
    log.trace("::getAllJobFiles(jobID={})", jobID);

    var jobDir   = Path.of(Config.getInstance().getJobMountPath(), jobID).toFile();
    var children = jobDir.listFiles();

    if (children == null)
      return Collections.emptyList();

    return Arrays.asList(children);
  }
}
