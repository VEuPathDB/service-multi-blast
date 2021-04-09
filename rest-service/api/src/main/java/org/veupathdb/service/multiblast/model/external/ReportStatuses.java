package org.veupathdb.service.multiblast.model.external;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;

public class ReportStatuses
{
  private final Map<BlastReportType, ReportStatus> values;

  public ReportStatuses() {
    this.values = new HashMap<>(BlastReportType.values().length);
    for (var v : BlastReportType.values())
      values.put(v, ReportStatus.NeverRun);
  }

  @JsonValue
  public Map<BlastReportType, ReportStatus> getValues() {
    return values;
  }
}
