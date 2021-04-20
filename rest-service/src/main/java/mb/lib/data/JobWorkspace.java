package mb.lib.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;

import mb.lib.model.HashID;

public class JobWorkspace
{
  private static final String ResultFile = "report.asn1";
  private static final String ErrorFile  = "error.log";

  private final HashID jobID;
  private final Path   workspace;

  public JobWorkspace(HashID jobID, Path workspace) {
    this.jobID     = jobID;
    this.workspace = workspace;
  }

  public Path filePath(String file) {
    return workspace.resolve(file);
  }

  public boolean fileExists(String file) {
    return filePath(file).toFile().exists();
  }

  public Path resultPath() {
    return filePath(ResultFile);
  }

  public boolean resultExists() {
    return fileExists(ResultFile);
  }

  public Path errorFilePath() {
    return filePath(ErrorFile);
  }

  public boolean errorFileExists() {
    return fileExists(ErrorFile);
  }

  public String errorText() {
    var errorPath = errorFilePath();
    var errorFile = errorPath.toFile();

    if (!errorFile.canRead())
      throw new IllegalStateException("Cannot read job error log for job " + jobID);

    try {
      return Files.readString(errorPath);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to open job error log for job " + jobID);
    }
  }

  /**
   * Generates a path to the workspace of the report identified by
   * {@code reportID}.
   * <p>
   * Note, this method does not check if the workspace actually exists, it
   * simply generates a path.
   *
   * @param reportID ID of the report for which a workspace path should be
   *                 generated.
   *
   * @return The generated report workspace path.
   */
  public Path reportPath(HashID reportID) {
    return filePath(reportID.string());
  }

  /**
   * Verifies that there exists a report workspace for the given report ID.
   *
   * @param reportID Report ID of the workspace to check for.
   *
   * @return {@code true} if a workspace exists for the given report ID,
   * otherwise {@code false}.
   */
  public boolean reportExists(HashID reportID) {
    return fileExists(reportID.toString());
  }

  public ReportWorkspace reportWorkspace(HashID reportID) throws Exception {
    return new ReportWorkspace(jobID, reportID, reportPath(reportID));
  }

  public void updateLastModified(OffsetDateTime time) throws Exception {
    workspace.toFile().setLastModified(time.toInstant().toEpochMilli());
  }
}
