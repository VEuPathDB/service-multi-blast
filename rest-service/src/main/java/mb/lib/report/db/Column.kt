package mb.lib.report.db

interface Column
{
  object FormatJob
  {
    const val Config    = "config"
    const val CreatedOn = "created_on"
    const val JobID     = "job_digest"
    const val QueueID   = "queue_id"
    const val ReportID  = "report_digest"
    const val Status    = "status"
  }

  object UserLink
  {
    const val ReportID    = "report_digest"
    const val UserID      = "user_id"
    const val Description = "description"
  }
}
