package org.veupathdb.service.multiblast.model.blast.tx;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.trait.*;

public interface TBlastxConfig
extends
  BlastConfig<TBlastxConfig>
, WithStrand<TBlastxConfig>
, WithQueryGeneticCode<TBlastxConfig>
, WithWordSize<TBlastxConfig>
, WithMaxIntronLength<TBlastxConfig>
, WithScoringMatrix<TBlastxConfig, TBlastxScoringMatrix>
, WithDbGeneticCode<TBlastxConfig>
, WithSubject<TBlastxConfig>
, WithSeg<TBlastxConfig>
, WithGenInfoIdList<TBlastxConfig>
, WithSequenceIdList<TBlastxConfig>
, WithTaxIds<TBlastxConfig>
, WithTaxIdList<TBlastxConfig>
, WithDbMasks<TBlastxConfig>
, WithCullingLimit<TBlastxConfig>
, WithBestHit<TBlastxConfig>
, WithSumStats<TBlastxConfig>
{
}
