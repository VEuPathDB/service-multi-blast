package mb.lib.blast.model.p;

import mb.lib.blast.model.BlastConfig;
import mb.lib.blast.model.trait.*;

public interface BlastpConfig
extends
  BlastConfig<BlastpConfig>
, WithTask<BlastpConfig, BlastpTask>
, WithWordSize<BlastpConfig>
, WithGapCosts<BlastpConfig>
, WithScoringMatrix<BlastpConfig, BlastpScoringMatrix>
, WithCompBasedStats<BlastpConfig>
, WithSubject<BlastpConfig>
, WithSeg<BlastpConfig>
, WithGenInfoIdList<BlastpConfig>
, WithSequenceIdList<BlastpConfig>
, WithTaxIdList<BlastpConfig>
, WithTaxIds<BlastpConfig>
, WithIdenticalProteinGroupList<BlastpConfig>
, WithDbMasks<BlastpConfig>
, WithCullingLimit<BlastpConfig>
, WithBestHit<BlastpConfig>
, WithGappedExtensionDropoff<BlastpConfig>
, WithUngapped<BlastpConfig>
, WithSmithWatermanAlignments<BlastpConfig>
{
}
