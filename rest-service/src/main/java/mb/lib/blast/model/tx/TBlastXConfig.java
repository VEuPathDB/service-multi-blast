package mb.lib.blast.model.tx;

import mb.lib.blast.model.BlastConfig;
import mb.lib.blast.model.trait.*;

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
