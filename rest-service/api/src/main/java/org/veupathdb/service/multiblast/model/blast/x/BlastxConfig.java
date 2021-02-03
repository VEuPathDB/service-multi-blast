package org.veupathdb.service.multiblast.model.blast.x;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.trait.*;

/**
 * Translated query against a protein database, AKA {@code blastx}.
 */
public interface BlastxConfig
extends
  BlastConfig<BlastxConfig>
, WithStrand<BlastxConfig>
, WithQueryGeneticCode<BlastxConfig>
, WithTask<BlastxConfig, BlastxTask>
, WithWordSize<BlastxConfig>
, WithGapCosts<BlastxConfig>
, WithMaxIntronLength<BlastxConfig>
, WithScoringMatrix<BlastxConfig, BlastxScoringMatrix>
, WithCompBasedStats<BlastxConfig>
, WithSubject<BlastxConfig>
, WithSeg<BlastxConfig>
, WithGenInfoIdList<BlastxConfig>
, WithSequenceIdList<BlastxConfig>
, WithTaxIds<BlastxConfig>
, WithTaxIdList<BlastxConfig>
, WithIdenticalProteinGroupList<BlastxConfig>
, WithDbMasks<BlastxConfig>
, WithCullingLimit<BlastxConfig>
, WithBestHit<BlastxConfig>
, WithSumStats<BlastxConfig>
, WithGappedExtensionDropoff<BlastxConfig>
, WithUngapped<BlastxConfig>
, WithSmithWatermanAlignments<BlastxConfig>
{
}
