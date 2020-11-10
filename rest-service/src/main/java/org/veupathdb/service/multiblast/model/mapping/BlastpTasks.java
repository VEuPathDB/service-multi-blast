package org.veupathdb.service.multiblast.model.mapping;

import java.util.Objects;
import javax.validation.constraints.NotNull;

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

  @Override
  public EnumMapping<Byte, BlastpTask> putRaw(Byte id, String value) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(value);

    return put(id, BlastpTask.fromString(value).orElseThrow());
  }
}
