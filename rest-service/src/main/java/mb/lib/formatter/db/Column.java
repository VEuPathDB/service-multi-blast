package mb.lib.formatter.db;

public interface Column
{
  interface FormatJob
  {
    String Config    = "config";
    String CreatedOn = "created_on";
    String JobID     = "job_digest";
    String QueueID   = "queue_id";
    String ReportID  = "report_digest";
    String Status    = "status";
  }

  interface UserLink
  {
    String ReportID    = "report_digest";
    String UserID      = "user_id";
    String Description = "description";
  }
}
