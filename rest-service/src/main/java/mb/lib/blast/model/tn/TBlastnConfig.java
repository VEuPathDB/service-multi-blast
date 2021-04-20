package mb.lib.blast.model.tn;

import mb.lib.blast.model.BlastConfig;
import mb.lib.blast.model.trait.*;

public interface TBlastnConfig
extends
  BlastConfig<TBlastnConfig>
, WithTask<TBlastnConfig, TBlastNTask>
, WithWordSize<TBlastnConfig>
, WithGapCosts<TBlastnConfig>
, WithDbGeneticCode<TBlastnConfig>
, WithMaxIntronLength<TBlastnConfig>
, WithScoringMatrix<TBlastnConfig, TBlastNScoringMatrix>
, WithCompBasedStats<TBlastnConfig>
, WithSubject<TBlastnConfig>
, WithSeg<TBlastnConfig>
, WithGenInfoIdList<TBlastnConfig>
, WithSequenceIdList<TBlastnConfig>
, WithTaxIds<TBlastnConfig>
, WithTaxIdList<TBlastnConfig>
, WithDbMasks<TBlastnConfig>
, WithCullingLimit<TBlastnConfig>
, WithBestHit<TBlastnConfig>
, WithSumStats<TBlastnConfig>
, WithGappedExtensionDropoff<TBlastnConfig>
, WithUngapped<TBlastnConfig>
, WithSmithWatermanAlignments<TBlastnConfig>
, WithPositionSpecificScoringMatrix<TBlastnConfig>
{
}
