package mb.lib.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mb.lib.model.ReportStatus;

public class ReportMeta
{
  private final List<String> files;

  @JsonCreator
  public ReportMeta(@JsonProperty("files") List<String> files) {
    this.files  = files;
  }

  public List<String> files() {
    return files;
  }
}
