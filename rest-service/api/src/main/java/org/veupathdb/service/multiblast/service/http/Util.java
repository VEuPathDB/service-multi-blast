package org.veupathdb.service.multiblast.service.http;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

import mb.lib.db.model.DBJobStatus;
import mb.lib.extern.model.QueueJobStatus;
import org.veupathdb.service.multiblast.model.internal.JobStatus;

public class Util
{
  public static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }

  public static QueueJobStatus convert(DBJobStatus status) {
    return switch (status) {
      case Completed -> QueueJobStatus.Completed;
      case Errored   -> QueueJobStatus.Errored;
      case Running   -> QueueJobStatus.InProgress;
      case Queued    -> QueueJobStatus.Queued;
    };
  }

  public static DBJobStatus convert(QueueJobStatus status) {
    return switch (status) {
      case Completed  -> DBJobStatus.Completed;
      case Errored    -> DBJobStatus.Errored;
      case InProgress -> DBJobStatus.Running;
      case Queued     -> DBJobStatus.Queued;
    };
  }

  static JobStatus convStatus(QueueJobStatus stat) {
    return switch (stat) {
      case Completed  -> JobStatus.Completed;
      case Errored    -> JobStatus.Errored;
      case Queued     -> JobStatus.Queued;
      case InProgress -> JobStatus.InProgress;
    };
  }

}
