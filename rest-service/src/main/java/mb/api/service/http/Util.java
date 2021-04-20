package mb.api.service.http;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

import mb.lib.db.model.DBJobStatus;
import mb.lib.queue.model.QueueJobStatus;
import mb.lib.model.JobStatus;

public class Util
{
  public static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }

  public static QueueJobStatus convert(DBJobStatus status) {
    return switch (status) {
      case DBJobStatus.Completed -> QueueJobStatus.Completed;
      case DBJobStatus.Errored   -> QueueJobStatus.Errored;
      case DBJobStatus.Running   -> QueueJobStatus.InProgress;
      case DBJobStatus.Queued    -> QueueJobStatus.Queued;
    };
  }

  public static DBJobStatus convert(QueueJobStatus status) {
    return switch (status) {
      case QueueJobStatus.Completed  -> DBJobStatus.Completed;
      case QueueJobStatus.Errored    -> DBJobStatus.Errored;
      case QueueJobStatus.InProgress -> DBJobStatus.Running;
      case QueueJobStatus.Queued     -> DBJobStatus.Queued;
    };
  }

  public static JobStatus convStatus(QueueJobStatus stat) {
    return switch (stat) {
      case QueueJobStatus.Completed  -> JobStatus.Completed;
      case QueueJobStatus.Errored    -> JobStatus.Errored;
      case QueueJobStatus.Queued     -> JobStatus.Queued;
      case QueueJobStatus.InProgress -> JobStatus.InProgress;
    };
  }

}
