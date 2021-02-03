package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

/**
 * AKA -in_pssm
 */
public interface WithPositionSpecificScoringMatrix<T> extends CliOptions
{
  File getPositionSpecificScoringMatrixFile();
  T setPositionSpecificScoringMatrixFile(File f);
}
