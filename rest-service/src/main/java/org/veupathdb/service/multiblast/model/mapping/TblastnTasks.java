package org.veupathdb.service.multiblast.model.mapping;

import javax.validation.constraints.NotNull;

import org.veupathdb.service.multiblast.model.blast.tn.TBlastnTask;

public class TblastnTasks extends EnumMap<Byte, TBlastnTask>
{
  private static TblastnTasks instance;

  private TblastnTasks() {
    super(TBlastnTask.values().length);
  }

  @NotNull
  @Override
  public EnumMapping<Byte, TBlastnTask> putRaw(@NotNull Byte id, @NotNull String value) {
    return put(id, TBlastnTask.fromString(value).orElseThrow());
  }

  public static TblastnTasks getInstance() {
    if (instance == null)
      instance = new TblastnTasks();

    return instance;
  }
}
