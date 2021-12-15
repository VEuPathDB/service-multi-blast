package mb.api.controller.resources

internal object Paths
{
  const val Jobs        = "/jobs"
  const val JobByID     = "/{${Vars.JobID}}"
  const val JobQuery    = "$JobByID/query"
  const val JobError    = "$JobByID/error"
  const val Reports     = "/reports"
  const val ReportByID  = "/{${Vars.ReportID}}"
  const val ReportFiles = "$ReportByID/files"
  const val ReportData  = "$ReportFiles/{${Vars.FileName}}"
}

internal object Vars {
  const val JobID    = "job-id"
  const val ReportID = "report-id"
  const val FileName = "file-name"
}
