package org.veupathdb.service.multiblast.model.blast;

import java.util.Collection;

public interface BlastReportFormat
{
  BlastReportType getType();

  String getDelimiter();

  BlastReportField[] getReportFields();
}
