package mb.lib.extern.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QueueJobStatus
{
  Completed,
  Errored,
  Queued,
  InProgress;

  @JsonCreator
  public static QueueJobStatus fromString(String value) {
    var status = value.toLowerCase();

    if ("completed".equals(status))
      return QueueJobStatus.Completed;

    if (status.contains("fail"))
      return QueueJobStatus.Errored;

    if ("grabbed".equals(status) || "claimed".equals(status))
      return QueueJobStatus.InProgress;

    if ("waiting".equals(status))
      return QueueJobStatus.Queued;

    throw new IllegalArgumentException(String.format("Invalid JobStatus value \"%s\".", value));
  }

  @Override
  public String toString() {
    return name();
  }
}
