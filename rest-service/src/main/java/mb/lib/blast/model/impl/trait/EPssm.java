package mb.lib.blast.model.impl.trait;

import java.io.File;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithPositionSpecificScoringMatrix;
import mb.api.service.cli.CliBuilder;

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
