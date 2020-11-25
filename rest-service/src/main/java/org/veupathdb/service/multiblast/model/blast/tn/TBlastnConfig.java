package org.veupathdb.service.multiblast.model.blast.tn;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.trait.*;

public interface TBlastnConfig
extends
  BlastConfig<TBlastnConfig>
, WithTask<TBlastnConfig, TBlastnTask>
, WithWordSize<TBlastnConfig>
, WithGapCosts<TBlastnConfig>
, WithDbGeneticCode<TBlastnConfig>
, WithMaxIntronLength<TBlastnConfig>
, WithScoringMatrix<TBlastnConfig, TBlastnScoringMatrix>
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
