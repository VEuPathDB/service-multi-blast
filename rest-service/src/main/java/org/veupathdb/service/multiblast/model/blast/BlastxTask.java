package org.veupathdb.service.multiblast.model.blast;

public enum BlastxTask
{
  BLASTX("blastx"),
  BLASTX_FAST("blastx-fast");

  private static final BlastxTask def = BLASTX;

  private final String value;

  BlastxTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BlastxTask getDefault() {
    return def;
  }

  @Override
  public String toString() {
    return value;
  }
}
