package org.veupathdb.service.multiblast.model.mapping;

import java.util.Objects;

import org.veupathdb.service.multiblast.model.blast.ToolOption;

public class BlastOptions extends EnumMap<Short, ToolOption>
{
  private static BlastOptions instance;

  private BlastOptions() {
    super(ToolOption.values().length);
  }

  @Override
  public EnumMapping<Short, ToolOption> putRaw(Short id, String value) {
    return put(id, ToolOption.fromString(Objects.requireNonNull(value)));
  }

  public static BlastOptions getInstance() {
    if (instance == null)
      instance = new BlastOptions();

    return instance;
  }
}
