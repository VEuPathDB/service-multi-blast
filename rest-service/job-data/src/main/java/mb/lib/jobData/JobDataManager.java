package mb.lib.jobData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import mb.lib.config.Config;

public class JobDataManager
{
  private static final Config conf = Config.getInstance();
  private static final String jobErrorFile = "error.log";

  public static boolean jobDataExists(String jobID) {
    var jobDir = Path.of(conf.getMountPath(), jobID).toFile();

    if (!jobDir.exists())
      return false;

    if (!jobDir.isDirectory())
      throw new IllegalStateException("Job path exists but is not a directory for job " + jobID);

    return true;
  }

  public static Path createJobWorkspace(String jobID) throws Exception {

  }

  public static File getJobFile(String jobID, String fileName) throws Exception {
    return Path.of(conf.getMountPath(), jobID, fileName).toFile();
  }

  public static File getJobFile(String jobID, File file) throws Exception {
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
   *         contents.
   */
  public static Optional<InputStream> getJobError(String jobID) {
    var errorLog = Path.of(conf.getMountPath(), jobID, jobErrorFile).toFile();

    if (!errorLog.exists())
      return Optional.empty();
    if (!errorLog.canRead())
      throw new IllegalStateException("Cannot read job error log for job " + jobID);

    try {
      return Optional.of(new FileInputStream(errorLog));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Failed to open job error log for job " + jobID);
    }
  }

  /**
   * Wipe out a job directory and it's contents.
   *
   * @param jobID ID of the job whose directory should be erased.
   */
  public static void deleteJobData(String jobID) throws Exception {
    var path = Path.of(conf.getMountPath(), jobID);

    Files.walk(path)
      .sorted(Comparator.reverseOrder())
      .map(Path::toFile)
      .forEach(File::delete);
  }

  public static Collection<File> getJobUserFiles(String jobID) {
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
    var jobDir   = Path.of(Config.getInstance().getMountPath(), jobID).toFile();
    var children = jobDir.listFiles();

    if (children == null)
      return Collections.emptyList();

    return Arrays.asList(children);
  }
}
