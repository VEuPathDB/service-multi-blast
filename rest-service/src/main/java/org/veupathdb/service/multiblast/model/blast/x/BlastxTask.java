package org.veupathdb.service.multiblast.model.blast.x;

public enum BlastxTask
{
  BlastX("blastx"),
  BlastXFast("blastx-fast");

  private static final BlastxTask def = BlastX;

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

  public static BlastxTask fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException();
  }

  @Override
  public String toString() {
    return value;
  }
}
