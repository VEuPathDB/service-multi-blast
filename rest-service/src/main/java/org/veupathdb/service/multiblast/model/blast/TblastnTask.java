package org.veupathdb.service.multiblast.model.blast;

public enum TblastnTask
{
  TBLASTN("tblastn"),
  TBLASTN_FAST("tblastn-fast");

  private static final TblastnTask def = TBLASTN;

  private final String value;

  TblastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static TblastnTask getDefault() {
    return def;
  }

  @Override
  public String toString() {
    return value;
  }
}
