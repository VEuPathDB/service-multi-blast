package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithBestHit<T> extends CliOptions
{
  Double getBestHitOverhang();
  T setBestHitOverhang(Double v);

  Double getBestHitScoreEdge();
  T setBestHitScoreEdge(Double v);

  boolean isSubjectBestHitEnabled();
  T enableSubjectBestHit(boolean b);
}
