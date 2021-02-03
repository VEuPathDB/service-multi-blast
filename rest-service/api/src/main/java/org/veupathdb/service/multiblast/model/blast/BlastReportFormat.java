package org.veupathdb.service.multiblast.model.blast;

public interface BlastReportFormat
{
  BlastReportType getType();

  String getDelimiter();

  BlastReportField[] getReportFields();
}
