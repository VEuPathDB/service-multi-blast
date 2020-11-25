package org.veupathdb.service.multiblast.model.mapping;

import org.veupathdb.service.multiblast.model.internal.JobStatus;

public class JobStatuses extends EnumMap<Byte, JobStatus>
{
  private static JobStatuses instance;

  private JobStatuses() {
    super(JobStatus.values().length);
  }

  @Override
  public EnumMapping<Byte, JobStatus> putRaw(Byte id, String value) {
    return put(id, JobStatus.unsafeFromString(value));
  }

  public static JobStatuses getInstance() {
    if (instance == null)
      instance = new JobStatuses();

    return instance;
  }
}
