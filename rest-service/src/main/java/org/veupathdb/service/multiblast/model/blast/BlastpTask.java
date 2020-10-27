package org.veupathdb.service.multiblast.model.blast;

public enum BlastpTask
{
  BLASTP("blastp"),
  BLASTP_FAST("blastp-fast"),
  BLASTP_SHORT("blastp-short");

  private static final BlastpTask def = BLASTP;

  private final String value;

  BlastpTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BlastpTask getDefault() {
    return def;
  }

  @Override
  public String toString() {
    return value;
  }
}
