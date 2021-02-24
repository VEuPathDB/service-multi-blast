package org.veupathdb.service.multiblast.service.conv;

import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.impl.BlastNConfigImpl;
import org.veupathdb.service.multiblast.model.blast.impl.DustImpl;
import org.veupathdb.service.multiblast.model.blast.n.BlastNTask;
import org.veupathdb.service.multiblast.model.blast.n.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.n.DcTemplateType;
import org.veupathdb.service.multiblast.model.blast.n.Dust;

class BlastnConverter
{
  private static BlastnConverter instance;

  static BlastnConverter getInstance() {
    if (instance == null)
      return instance = new BlastnConverter();

    return instance;
  }

  static IOBlastnConfig toExternal(IOBlastnConfig out, BlastnConfig conf) {
    return getInstance().internalToExternal(out, conf);
  }

  static IOBlastnDust toExternal(Dust val) {
    if (val == null)
      return null;

    var out = new IOBlastnDustImpl();

    out.setEnable(true);
    out.setLevel((short) val.getLevel());
    out.setLinker((short) val.getLinker());
    out.setWindow((short) val.getWindow());

    return out;
  }

  static IOBlastnTask toExternal(BlastNTask val) {
    if (val == null)
      return null;

    return switch (val) {
      case BlastN -> IOBlastnTask.BLASTN;
      case BlastNShort -> IOBlastnTask.BLASTNSHORT;
      case DiscontiguousMegablast -> IOBlastnTask.DCMEGABLAST;
      case Megablast -> IOBlastnTask.MEGABLAST;
      case RMBlastN -> throw new RuntimeException("rmblastn is currently disallowed.");
    };
  }

  static IOBlastnDcTemplateType toExternal(DcTemplateType val) {
    if (val == null)
      return null;

    return switch (val) {
      case Coding -> IOBlastnDcTemplateType.CODING;
      case Optimal -> IOBlastnDcTemplateType.OPTIMAL;
      case Both -> IOBlastnDcTemplateType.BOTH;
    };
  }

  static BlastNTask toInternal(IOBlastnTask val) {
    if (val == null)
      return null;

    return switch (val) {
      case MEGABLAST -> BlastNTask.Megablast;
      case DCMEGABLAST -> BlastNTask.DiscontiguousMegablast;
      case BLASTN -> BlastNTask.BlastN;
      case BLASTNSHORT -> BlastNTask.BlastNShort;
    };
  }

  /**
   * Converts a raml generated Dust representation to the representation used
   * internally by the service.
   * <p>
   * <b>NOTE</b> this method assumes the value has already passed validation by
   * the time it gets here.  If the IO value has not been validated, this
   * method may throw a null pointer exception.
   * <p>
   * If the input value to this method is null, or marked as disabled, this
   * method will return null.
   *
   * @param val external dust representation
   *
   * @return internal dust representation
   */
  static Dust toInternal(IOBlastnDust val) {
    if (val == null || !val.getEnable())
      return null;

    return new DustImpl(val.getLevel(), val.getLinker(), val.getWindow());
  }

  static DcTemplateType toInternal(IOBlastnDcTemplateType val) {
    if (val == null) {
      return null;
    }

    return switch (val) {
      case CODING -> DcTemplateType.Coding;
      case OPTIMAL -> DcTemplateType.Optimal;
      case BOTH -> DcTemplateType.Both;
    };
  }

  static BlastnConfig toInternal(IOBlastnConfig val) {
    return getInstance().externalToInternal(val);
  }

  BlastnConfig externalToInternal(IOBlastnConfig val) {
    if (val == null) {
      return null;
    }

    return new BlastNConfigImpl()
      .setStrand(BCC.toInternal(val.getStrand()))
      .setTask(toInternal(val.getTask()))
      .setWordSize(val.getWordSize())
      .setGapCostOpen(val.getGapOpen())
      .setGapCostExtend(val.getGapExtend())
      .setNucleotideMismatchPenalty(val.getPenalty())
      .setNucleotideMatchReward(val.getReward())
      .enableMegablastDbIndexUsage(val.getUseIndex())
      .setMegablastDbIndexName(val.getIndexName())
      .setDust(toInternal(val.getDust()))
      .setWindowMaskerTaxId(val.getWindowMaskerTaxid())
      .enableSoftMasking(val.getSoftMasking())
      .setTaxIDs(val.getTaxIds())
      .setNegativeTaxIds(val.getNegativeTaxIds())
      .setDbSoftMaskAlgorithmId(val.getDbSoftMask())
      .setDbHardMaskAlgorithmId(val.getDbHardMask())
      .setPercentIdentity(val.getPercIdentity())
      .setCullingLimit(val.getCullingLimit())
      .setBestHitOverhang(val.getBestHitOverhang())
      .setBestHitScoreEdge(val.getBestHitScoreEdge())
      .enableSubjectBestHit(BCC.nullToFalse(val.getSubjectBestHit()))
      .setDiscontiguousMegablastTemplateType(toInternal(val.getTemplateType()))
      .setDiscontiguousMegablastTemplateLength(val.getTemplateLength())
      .enableSumStatistics(val.getSumStats())
      .setExtensionDropoffPreliminaryGapped(val.getXDropGap())
      .setExtensionDropoffFinalGapped(val.getXDropGapFinal())
      .enableNonGreedyDynamicProgramExtension(BCC.nullToFalse(val.getNoGreedy()))
      .setMinRawGappedScore(val.getMinRawGappedScore())
      .enableUngappedAlignmentOnly(BCC.nullToFalse(val.getUngapped()))
      .setMultiHitWindowSize(val.getWindowSize())
      .setOffDiagonalRange(val.getOffDiagonalRange());
  }

  IOBlastnConfig internalToExternal(IOBlastnConfig out, BlastnConfig conf) {
    if (conf == null)
      return null;

    out.setStrand(BCC.toExternal(conf.getStrand()));
    out.setTask(toExternal(conf.getTask()));
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapCostOpen());
    out.setGapExtend(conf.getGapCostExtend());
    out.setPenalty(conf.getNucleotideMismatchPenalty());
    out.setReward(conf.getNucleotideMatchReward());
    out.setUseIndex(conf.isMegablastDbIndexUsageEnabled());
    out.setIndexName(conf.getMegablastDbIndexName());
    out.setDust(toExternal(conf.getDust()));
    out.setWindowMaskerTaxid(conf.getWindowMaskerTaxId());
    out.setSoftMasking(conf.isSoftMaskingEnabled());
    out.setTaxIds(BCC.arrayToList(conf.getTaxIds()));
    out.setNegativeTaxIds(BCC.arrayToList(conf.getNegativeTaxIds()));
    out.setDbSoftMask(conf.getDbSoftMaskAlgorithmId());
    out.setDbHardMask(conf.getDbHardMaskAlgorithmId());
    out.setPercIdentity(conf.getPercentIdentity());
    out.setCullingLimit(conf.getCullingLimit());
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.isSubjectBestHitEnabled() ? true : null);
    out.setTemplateType(toExternal(conf.getDiscontiguousMegablastTemplateType()));
    out.setTemplateLength(conf.getDiscontiguousMegablastTemplateLength());
    out.setSumStats(conf.isSumStatisticsEnabled());
    out.setXDropGap(conf.getExtensionDropoffPreliminaryGapped());
    out.setXDropGapFinal(conf.getExtensionDropoffFinalGapped());
    out.setNoGreedy(conf.isNonGreedyDynamicProgramExtensionEnabled() ? true : null);
    out.setMinRawGappedScore(conf.getMinRawGappedScore());
    out.setUngapped(conf.isUngappedAlignmentOnlyEnabled() ? true : null);
    out.setWindowSize(conf.getMultiHitWindowSize());
    out.setOffDiagonalRange(conf.getOffDiagonalRange());

    return out;
  }
}
