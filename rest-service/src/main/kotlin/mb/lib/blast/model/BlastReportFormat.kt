package mb.lib.blast.model;

interface BlastReportFormat {
  val type: BlastReportType

  val delimiter: String

  val reportFields: Array<BlastReportField>
}
