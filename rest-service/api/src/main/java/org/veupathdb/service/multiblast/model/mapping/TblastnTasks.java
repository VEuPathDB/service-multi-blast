package org.veupathdb.service.multiblast.model.mapping;

import javax.validation.constraints.NotNull;

import org.veupathdb.service.multiblast.model.blast.tn.TBlastNTask;

public class TblastnTasks extends EnumMap<Byte, TBlastNTask>
{
  private static TblastnTasks instance;

  private TblastnTasks() {
    super(TBlastNTask.values().length);
  }

  @NotNull
  @Override
  public EnumMapping<Byte, TBlastNTask> putRaw(@NotNull Byte id, @NotNull String value) {
    return put(id, TBlastNTask.fromString(value));
  }

  public static TblastnTasks getInstance() {
    if (instance == null)
      instance = new TblastnTasks();

    return instance;
  }
}
