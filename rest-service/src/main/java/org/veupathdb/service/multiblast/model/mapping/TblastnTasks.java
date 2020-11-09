package org.veupathdb.service.multiblast.model.mapping;

import javax.validation.constraints.NotNull;

import org.veupathdb.service.multiblast.model.blast.TblastnTask;

public class TblastnTasks extends EnumMap<Byte, TblastnTask>
{
  private static TblastnTasks instance;

  private TblastnTasks() {
    super(TblastnTask.values().length);
  }

  @NotNull
  @Override
  public EnumMapping<Byte, TblastnTask> putRaw(@NotNull Byte id, @NotNull String value) {
    return put(id, TblastnTask.fromString(value).orElseThrow());
  }

  public static TblastnTasks getInstance() {
    if (instance == null)
      instance = new TblastnTasks();

    return instance;
  }
}
