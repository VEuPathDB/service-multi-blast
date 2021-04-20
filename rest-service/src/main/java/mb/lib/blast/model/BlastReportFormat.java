package mb.lib.blast.model;

public interface BlastReportFormat
{
  BlastReportType getType();

  String getDelimiter();

  BlastReportField[] getReportFields();
}
