package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithScoringMatrix<T, E extends Enum<E>> extends CliOptions
{
  E getScoringMatrix();
  T setScoringMatrix(E matrix);

  Double getScoreThreshold();
  T setScoreThreshold(Double d);
}
