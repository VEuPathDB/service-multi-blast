package mb.lib.db.model;

import mb.api.model.reports.ReportRequest;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

public interface FormatJobStatus extends Row
{
  HashID reportID();

  long userID();

  ReportRequest config();

  int queueID();

  JobStatus status();
}
