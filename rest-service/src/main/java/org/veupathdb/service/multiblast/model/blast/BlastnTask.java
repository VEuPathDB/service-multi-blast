package org.veupathdb.service.multiblast.model.blast;

public enum  BlastnTask
{
  BLASTN("blastn"),
  BLASTN_SHORT("blastn-short"),
  DC_MEGABLAST("dc-megablast"),
  MEGABLAST("megablast"),
  RMBLASTN("rmblast");

  private static final BlastnTask def = MEGABLAST;

  private final String value;

  BlastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BlastnTask getDefault() {
    return def;
  }

  @Override
  public String toString() {
    return value;
  }
}
