package mb.api.controller.resources

internal object Paths
{
  const val BlastQueue  = "/blast"
  const val Jobs        = "/jobs"
  const val JobByID     = "/{${Vars.JobID}}"
  const val JobQuery    = "$JobByID/query"
  const val JobError    = "$JobByID/error"
  const val Queues      = "/queues"
  const val Reports     = "/reports"
  const val ReportByID  = "/{${Vars.ReportID}}"
  const val ReportData  = "$ReportFiles/{${Vars.FileName}}"
  const val ReportFiles = "$ReportByID/files"
  const val ReportQueue = "/reports"
}

internal object Vars {
  const val JobID    = "job-id"
  const val ReportID = "report-id"
  const val FileName = "file-name"
}
