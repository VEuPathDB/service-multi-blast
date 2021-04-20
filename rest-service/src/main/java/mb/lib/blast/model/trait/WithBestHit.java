package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithBestHit<T> extends CliOptions
{
  Double getBestHitOverhang();
  T setBestHitOverhang(Double v);

  Double getBestHitScoreEdge();
  T setBestHitScoreEdge(Double v);

  boolean isSubjectBestHitEnabled();
  T enableSubjectBestHit(boolean b);
}
