package mb.lib.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import mb.lib.model.ReportStatus;

public class ReportMeta
{
  private final ReportStatus status;
  private final List<String> files;

  @JsonCreator
  public ReportMeta(ReportStatus status, List<String> files) {
    this.status = status;
    this.files  = files;
  }

  public ReportStatus status() {
    return status;
  }

  public List<String> files() {
    return files;
  }
}
