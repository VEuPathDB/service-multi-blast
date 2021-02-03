package org.veupathdb.service.multiblast.model.blast.tx;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.trait.*;

public interface TBlastXConfig
extends
  BlastConfig<TBlastXConfig>
, WithStrand<TBlastXConfig>
, WithQueryGeneticCode<TBlastXConfig>
, WithWordSize<TBlastXConfig>
, WithMaxIntronLength<TBlastXConfig>
, WithScoringMatrix<TBlastXConfig, TBlastxScoringMatrix>
, WithDbGeneticCode<TBlastXConfig>
, WithSubject<TBlastXConfig>
, WithSeg<TBlastXConfig>
, WithGenInfoIdList<TBlastXConfig>
, WithSequenceIdList<TBlastXConfig>
, WithTaxIds<TBlastXConfig>
, WithTaxIdList<TBlastXConfig>
, WithDbMasks<TBlastXConfig>
, WithCullingLimit<TBlastXConfig>
, WithBestHit<TBlastXConfig>
, WithSumStats<TBlastXConfig>
{
}
