package mb.lib.extern;

public enum JobStatus
{
  Completed,
  Errored,
  Queued,
  InProgress;

  static JobStatus fromString(String value) {
    var status = value.toLowerCase();

    if ("completed".equals(status))
      return JobStatus.Completed;

    if (status.contains("fail"))
      return JobStatus.Errored;

    if ("grabbed".equals(status))
      return JobStatus.InProgress;

    return JobStatus.Queued;
  }
}
