package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithScoringMatrix;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EScoringMatrix<E extends Enum<E>> implements WithScoringMatrix<Void, E>
{
  private E scoringMatrix;
  private Double threshold;

  @Override
  public E getScoringMatrix() {
    return scoringMatrix;
  }

  @Override
  public Void setScoringMatrix(E matrix) {
    scoringMatrix = matrix;
    return null;
  }

  @Override
  public Double getScoreThreshold() {
    return threshold;
  }

  @Override
  public Void setScoreThreshold(Double d) {
    threshold = d;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.ScoringMatrix, scoringMatrix)
      .appendNonNull(ToolOption.Threshold, threshold);
  }
}
