package mb.lib.blast.model.trait;

import java.io.File;

import mb.api.service.cli.CliOptions;

/**
 * AKA -in_pssm
 */
public interface WithPositionSpecificScoringMatrix<T> extends CliOptions
{
  File getPositionSpecificScoringMatrixFile();
  T setPositionSpecificScoringMatrixFile(File f);
}
