package org.veupathdb.service.multiblast.model.blast;

public enum BlastTool
{
  BLASTN,
  BLASTP,
  BLASTX,
  PSIBLAST,
  RPSBLAST,
  RPSTBLASTN,
  TBLASTN,
  TBLASTX;

  public String value() {
    return name().toLowerCase();
  }

  @Override
  public String toString() {
    return value();
  }
}
