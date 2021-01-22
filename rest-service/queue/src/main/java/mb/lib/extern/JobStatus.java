package mb.lib.extern;

public enum JobStatus
{
  Completed,
  Errored,
  Queued,
  InProgress,
  Unknown;

  static JobStatus fromString(String value) {
    var status = value.toLowerCase();

    if ("completed".equals(status))
      return JobStatus.Completed;

    if (status.contains("fail"))
      return JobStatus.Errored;

    if ("grabbed".equals(status))
      return JobStatus.InProgress;

    if ("waiting".equals(status))
      return JobStatus.Queued;

    return JobStatus.Unknown;
  }
}
