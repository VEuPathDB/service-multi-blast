package org.veupathdb.service.multiblast.model.mapping;

import org.veupathdb.service.multiblast.model.blast.n.BlastNTask;

public class BlastnTasks extends EnumMap<Byte, BlastNTask>
{
  private static BlastnTasks instance;

  private BlastnTasks() {
    super(BlastNTask.values().length);
  }

  public static BlastnTasks getInstance() {
    if (instance == null)
      instance = new BlastnTasks();

    return instance;
  }

  @Override
  public EnumMapping<Byte, BlastNTask> putRaw(Byte id, String value) {
    return this.put(id, BlastNTask.fromString(value));
  }
}
