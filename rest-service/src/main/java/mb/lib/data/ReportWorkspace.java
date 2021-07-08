package mb.lib.data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;

import mb.lib.model.HashID;
import mb.lib.util.JSON;

public class ReportWorkspace
{
  private static final String ReportZip = "report.zip";
  private static final String MetaJson  = "meta.json";

  private final HashID     jobID;
  private final HashID     reportID;
  private final Path       workspace;

  private ReportMeta meta;

  public ReportWorkspace(HashID jobID, HashID reportID, Path workspace) {
    this.jobID     = jobID;
    this.reportID  = reportID;
    this.workspace = workspace;
  }

  /**
   * Returns the path to the report zip file.
   * <p>
   * This method does not verify the target file exists, it simply returns the
   * path.
   *
   * @return Path to the report zip file.
   */
  public Path zipPath() {
    return workspace.resolve(ReportZip);
  }

  /**
   * Verifies that the report zip file exists.
   *
   * @return {@code true} if the report zip file exists, otherwise
   * {@code false}.
   */
  public boolean zipFileExists() {
    return zipPath().toFile().exists();
  }

  /**
   * Returns a raw byte stream of the report zip file.
   *
   * @return A byte stream of the report zip file.
   */
  public InputStream getZipStream() throws Exception {
    return new FileInputStream(zipPath().toFile());
  }

  public Path getReportMetaPath() {
    return workspace.resolve(MetaJson);
  }

  public ReportMeta getReportMeta() throws Exception {
    if (meta == null)
      meta = JSON.parse(
        new FileInputStream(getReportMetaPath().toFile()),
        ReportMeta.class
      );

    return meta;
  }

  public boolean reportMetaExists() {
    return fileExists(MetaJson);
  }

  public Path getFilePath(String file) {
    return workspace.resolve(file);
  }

  public boolean fileExists(String file) {
    return getFilePath(file).toFile().exists();
  }

  public InputStream getFileStream(String file) throws Exception {
    return new FileInputStream(getFilePath(file).toFile());
  }
}
