package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithCullingLimit;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ECullingLimit implements WithCullingLimit<Void>
{
  /**
   * <pre>
   * -culling_limit <Integer, >=0>
   *   If the query range of a hit is enveloped by that of at least this many
   *   higher-scoring hits, delete the hit
   * </pre>
   * <p>
   * Incompatible with:
   * <ul>
   *   <li><code>best_hit_overhang</code></li>
   *   <li><code>best_hit_score_edge</code></li>
   * </ul>
   */
  private Integer cullingLimit;

  @Override
  public Integer getCullingLimit() {
    return cullingLimit;
  }

  @Override
  public Void setCullingLimit(Integer v) {
    cullingLimit = v;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.CullingLimit, cullingLimit);
  }
}
