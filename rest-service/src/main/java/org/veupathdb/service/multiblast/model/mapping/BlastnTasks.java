package org.veupathdb.service.multiblast.model.mapping;

import javax.validation.constraints.NotNull;

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

  @NotNull
  @Override
  public EnumMapping<Byte, BlastnTask> putRaw(@NotNull Byte id, @NotNull String value) {
    return this.put(id, BlastnTask.fromString(value).orElseThrow());
  }
}
