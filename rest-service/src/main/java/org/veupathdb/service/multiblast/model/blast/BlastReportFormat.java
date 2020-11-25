package org.veupathdb.service.multiblast.model.blast;

import java.util.Collection;

public interface BlastReportFormat
{
  BlastReportType getType();
  BlastReportFormat setType(BlastReportType type);

  String getDelimiter();
  BlastReportFormat setDelimiter(String del);

  BlastReportField[] getReportFields();
  BlastReportFormat setReportFields(BlastReportField[] fields);
  BlastReportFormat setReportFields(Collection<BlastReportField> fields);
}
