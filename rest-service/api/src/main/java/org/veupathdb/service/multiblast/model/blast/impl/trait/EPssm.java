package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithPositionSpecificScoringMatrix;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EPssm implements WithPositionSpecificScoringMatrix<Void>
{
  private File pssm;

  @Override
  public File getPositionSpecificScoringMatrixFile() {
    return pssm;
  }

  @Override
  public Void setPositionSpecificScoringMatrixFile(File f) {
    pssm = f;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.InputPsiBlastCheckpointFile, pssm);
  }
}
