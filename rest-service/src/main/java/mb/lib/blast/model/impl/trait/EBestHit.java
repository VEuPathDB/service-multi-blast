package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithBestHit;
import mb.api.service.cli.CliBuilder;

public class EBestHit implements WithBestHit<Void>
{
  /**
   * {@code -best_hit_overhang <Real, (>0 and <0.5)>}
   * <p>
   * Best Hit algorithm overhang value (recommended value: 0.1)
   * <p>
   * Incompatible with:
   * <ul>
   *   <li><code>culling_limit</code></li>
   * </ul>
   * </pre>
   */
  private Double bestHitOverhang;

  /**
   * {@code -best_hit_score_edge <Real, (>0 and <0.5)>}
   * <p>
   * Best Hit algorithm score edge value (recommended value: 0.1)
   * <p>
   * Incompatible with
   * <ul>
   *   <li><code>culling_limit</code></li>
   * </ul>
   */
  private Double bestHitScoreEdge;

  /**
   * {@code -subject_besthit}
   * <p>
   * Turn on best hit per subject sequence.
   */
  private boolean subjectBestHit;

  @Override
  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  @Override
  public Void setBestHitOverhang(Double v) {
    bestHitOverhang = v;
    return null;
  }

  @Override
  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  @Override
  public Void setBestHitScoreEdge(Double v) {
    bestHitScoreEdge = v;
    return null;
  }

  @Override
  public boolean isSubjectBestHitEnabled() {
    return subjectBestHit;
  }

  @Override
  public Void enableSubjectBestHit(boolean b) {
    subjectBestHit = b;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.BestHitOverhang, bestHitOverhang)
      .appendNonNull(ToolOption.BestHitScoreEdge, bestHitScoreEdge);

    if (subjectBestHit)
      cli.append(ToolOption.SubjectBestHit);
  }
}
