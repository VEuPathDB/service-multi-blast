package org.veupathdb.service.multiblast.model.mapping;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.veupathdb.service.multiblast.model.blast.BlastTool;

public class BlastTools extends EnumMap<Byte, BlastTool>
{
  private static BlastTools instance;

  private BlastTools() {
    super(BlastTool.values().length);
  }

  public static BlastTools getInstance() {
    if (instance == null)
      instance = new BlastTools();

    return instance;
  }

  @Override
  public EnumMapping<Byte, BlastTool> putRaw(Byte id, String value) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(value);

    return put(id, BlastTool.fromString(value).orElseThrow());
  }
}
