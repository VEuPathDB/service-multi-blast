package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithCullingLimit;
import mb.api.service.cli.CliBuilder;

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
