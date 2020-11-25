package org.veupathdb.service.multiblast.model.mapping;

import java.util.Objects;

import org.veupathdb.service.multiblast.model.blast.x.BlastxTask;

public class BlastxTasks extends EnumMap<Byte, BlastxTask>
{
  private static BlastxTasks instance;

  private BlastxTasks() {
    super(BlastxTask.values().length);
  }

  public static BlastxTasks getInstance() {
    if (instance == null)
      instance = new BlastxTasks();

    return instance;
  }

  @Override
  public EnumMapping<Byte, BlastxTask> putRaw(Byte id, String value) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(value);

    return put(id, BlastxTask.fromString(value).orElseThrow());
  }
}
