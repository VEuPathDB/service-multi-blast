package org.veupathdb.service.multiblast.model.mapping;

import org.veupathdb.service.multiblast.model.blast.n.BlastnTask;

public class BlastnTasks extends EnumMap<Byte, BlastnTask>
{
  private static BlastnTasks instance;

  private BlastnTasks() {
    super(BlastnTask.values().length);
  }

  public static BlastnTasks getInstance() {
    if (instance == null)
      instance = new BlastnTasks();

    return instance;
  }

  @Override
  public EnumMapping<Byte, BlastnTask> putRaw(Byte id, String value) {
    return this.put(id, BlastnTask.fromString(value));
  }
}
