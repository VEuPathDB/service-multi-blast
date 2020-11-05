package org.veupathdb.service.multiblast.model.mapping;

import org.veupathdb.service.multiblast.model.blast.BlastxTask;

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
}
