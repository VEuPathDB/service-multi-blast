package org.veupathdb.service.multiblast.model.mapping;

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
}
