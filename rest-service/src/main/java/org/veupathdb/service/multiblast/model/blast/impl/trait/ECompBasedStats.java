package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithCompBasedStats;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ECompBasedStats implements WithCompBasedStats<Void>
{
  /**
   * {@code -comp_based_stats <String>}
   * <p>
   * <pre>
   * Use composition-based statistics:
   *   D or d: default (equivalent to 2 )
   *   0 or F or f: No composition-based statistics
   *   1: Composition-based statistics as in NAR 29:2994-3005, 2001
   *   2 or T or t : Composition-based score adjustment as in Bioinformatics 21:902-911, 2005, conditioned on sequence properties
   *   3: Composition-based score adjustment as in Bioinformatics 21:902-911, 2005, unconditionally
   * </pre>
   * <p>
   * Default = `2'
   */
  private CompBasedStats stats;

  @Override
  public CompBasedStats getCompBasedStatisticsType() {
    return stats;
  }

  @Override
  public Void setCompBasedStatisticsType(CompBasedStats c) {
    stats = c;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.CompositionBasedStats, stats);
  }
}
