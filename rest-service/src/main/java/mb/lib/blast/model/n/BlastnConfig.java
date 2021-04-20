package mb.lib.blast.model.n;

import java.nio.file.Path;

import mb.lib.blast.model.BlastConfig;
import mb.lib.blast.model.trait.*;

public interface BlastnConfig
extends
  BlastConfig<BlastnConfig>
, WithTask<BlastnConfig, BlastNTask>
, WithTaxIds<BlastnConfig>
, WithGenInfoIdList<BlastnConfig>
, WithSequenceIdList<BlastnConfig>
, WithTaxIdList<BlastnConfig>
, WithStrand<BlastnConfig>
, WithWordSize<BlastnConfig>
, WithGapCosts<BlastnConfig>
, WithSubject<BlastnConfig>
, WithDbMasks<BlastnConfig>
, WithCullingLimit<BlastnConfig>
, WithBestHit<BlastnConfig>
, WithSumStats<BlastnConfig>
, WithGappedExtensionDropoff<BlastnConfig>
, WithUngapped<BlastnConfig>
{
  Integer getNucleotideMatchReward();
  BlastnConfig setNucleotideMatchReward(Integer i);

  Integer getNucleotideMismatchPenalty();
  BlastnConfig setNucleotideMismatchPenalty(Integer i);

  Boolean isMegablastDbIndexUsageEnabled();
  BlastnConfig enableMegablastDbIndexUsage(Boolean b);

  String getMegablastDbIndexName();
  BlastnConfig setMegablastDbIndexName(String name);

  Dust getDust();
  BlastnConfig setDust(Dust d);

  Path getFilteringDbPath();
  BlastnConfig setFilteringDbPath(Path p);

  Integer getWindowMaskerTaxId();
  BlastnConfig setWindowMaskerTaxId(Integer id);

  Path getWindowMaskerDbPath();
  BlastnConfig setWindowMaskerDbPath(Path p);

  Double getPercentIdentity();
  BlastnConfig setPercentIdentity(Double d);

  DcTemplateType getDiscontiguousMegablastTemplateType();
  BlastnConfig setDiscontiguousMegablastTemplateType(DcTemplateType type);

  Byte getDiscontiguousMegablastTemplateLength();
  BlastnConfig setDiscontiguousMegablastTemplateLength(Byte len);

  boolean isNonGreedyDynamicProgramExtensionEnabled();
  BlastnConfig enableNonGreedyDynamicProgramExtension(boolean b);

  /**
   * Gets the configured minimum raw gapped score to keep an alignment in the
   * preliminary gapped and traceback stages.
   * <p>
   * If this option is unset, null will be returned.
   *
   * @return Int value if set, otherwise null.
   */
  Integer getMinRawGappedScore();

  /**
   * Sets the configured minimum raw gapped score to keep an alignment in the
   * preliminary gapped and traceback stages.
   * <p>
   * If this method is passed a null value, the option will be unset.
   *
   * @param i the new option value to set, or null to unset
   *
   * @return This BlastConfig instance.
   */
  BlastnConfig setMinRawGappedScore(Integer i);

  /**
   * Gets the currently configured number of off-diagonals to search for the 2nd
   * hit.
   * <p>
   * If this option is unset, null will be returned.

   * @return Int value if set, otherwise null.
   */
  Integer getOffDiagonalRange();

  /**
   * Sets the configured number of off-diagonals to search for the 2nd hit, use
   * {@code 0} or {@code null} to turn off
   * <p>
   * If this method is passed a null value, the option will be unset.
   *
   * @param val the new option value to set, or null to unset
   *
   * @return This BlastConfig instance.
   */
  BlastnConfig setOffDiagonalRange(Integer val);
}
