package org.veupathdb.service.multiblast.model.blast.p;

public enum BlastpTask
{
  BlastP("blastp"),
  BlastPFast("blastp-fast"),
  BlastPShort("blastp-short");

  private final String value;

  BlastpTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static BlastpTask fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException(); // TODO: print actual value
  }
}