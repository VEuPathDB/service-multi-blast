package org.veupathdb.service.multiblast.model.blast.p;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.trait.*;

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
