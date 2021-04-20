package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithScoringMatrix<T, E extends Enum<E>> extends CliOptions
{
  E getScoringMatrix();
  T setScoringMatrix(E matrix);

  Double getScoreThreshold();
  T setScoreThreshold(Double d);
}
