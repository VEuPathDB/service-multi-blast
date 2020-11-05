package org.veupathdb.service.multiblast.model.mapping;

import org.veupathdb.service.multiblast.model.blast.BlastpTask;

public class BlastpTasks extends EnumMap<Byte, BlastpTask>
{
  private static BlastpTasks instance;

  private BlastpTasks() {
    super(BlastpTask.values().length);
  }

  public static BlastpTasks getInstance() {
    if (instance == null)
      instance = new BlastpTasks();

    return instance;
  }
}
