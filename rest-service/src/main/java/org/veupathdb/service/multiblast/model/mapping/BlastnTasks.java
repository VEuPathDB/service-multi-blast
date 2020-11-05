package org.veupathdb.service.multiblast.model.mapping;

import org.veupathdb.service.multiblast.model.blast.BlastnTask;

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
}
