package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;
import org.veupathdb.service.multiblast.service.jobs.BlastnValidator;

public class BlastnConfig extends BlastConfig implements CLISerializable, Validatable
{
  private QueryStrand   strand = QueryStrand.BOTH;
  private BlastnTask    task   = BlastnTask.MEGABLAST;
  // TODO: Database fields
  private Integer       wordSize;
  private Integer       gapOpen;
  private Integer       gapExtend;
  private Integer       penalty;
  private Integer       reward;
  private Boolean       useIndex;
  private String        indexName;
  private File          subject;
  private QueryLocation subjectLoc;
  private OutFormat     outFmt;
  private Boolean       showGIs;
  private Dust          dust;
  private File          filteringDb;
  private Integer       windowMaskerTaxID;
  private File          windowMaskerDB;
  private Boolean       softMasking;
  private String        giList;
  private String        seqIdList;
  private String        negativeGiList;
  private String        negativeSeqIdList;
  private String        taxIds;
  private String        negativeTaxIds;
  private String        taxIdList;
  private String        negativeTaxIdList;
  private String        dbSoftMask;
  private String        dbHardMask;
  private Double        percIdentity;
  private Double        qCovHspPerc;
  private Integer       cullingLimit;
  private Double        bestHitOverhang;
  private Double        bestHitScoreEdge;
  private Boolean       subjectBestHit;
  private TemplateType  templateType;
  private Integer       templateLength;
  private Boolean       sumStats;

  @Override
  public ErrorMap validate() {
    return BlastnValidator.validate(this);
  }
}
