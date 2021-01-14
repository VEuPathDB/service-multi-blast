package org.veupathdb.service.multiblast.service.conv;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.IOBlastxConfig;
import org.veupathdb.service.multiblast.generated.model.IOBlastxScoringMatrix;
import org.veupathdb.service.multiblast.generated.model.IOBlastxTask;
import org.veupathdb.service.multiblast.model.blast.x.BlastxConfig;
import org.veupathdb.service.multiblast.model.blast.x.BlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.x.BlastxTask;
import org.veupathdb.service.multiblast.model.blast.impl.BlastXConfigImpl;

public class BlastxConverter
{
  private static final Logger          log = LogProvider.logger(BlastxConverter.class);
  private static       BlastxConverter instance;

  private BlastxConverter() {
  }

  public static BlastxConverter getInstance() {
    if (instance == null)
      return instance = new BlastxConverter();

    return instance;
  }

  static IOBlastxTask toExternal(BlastxTask val) {
    log.trace("#toExternal(BlastxTask)");

    if (val == null)
      return null;

    return switch (val) {
      case BlastX -> IOBlastxTask.BLASTX;
      case BlastXFast -> IOBlastxTask.BLASTXFAST;
    };
  }

  static IOBlastxScoringMatrix toExternal(BlastxScoringMatrix val) {
    log.trace("#toExternal(BlastxScoringMatrix)");

    if (val == null)
      return null;

    return switch (val) {
      case Blosum45 -> IOBlastxScoringMatrix.BLOSUM45;
      case Blosum50 -> IOBlastxScoringMatrix.BLOSUM50;
      case Blosum62 -> IOBlastxScoringMatrix.BLOSUM62;
      case Blosum80 -> IOBlastxScoringMatrix.BLOSUM80;
      case Blosum90 -> IOBlastxScoringMatrix.BLOSUM90;
      case Pam30 -> IOBlastxScoringMatrix.PAM30;
      case Pam70 -> IOBlastxScoringMatrix.PAM70;
      case Pam250 -> IOBlastxScoringMatrix.PAM250;
    };
  }

  static BlastxScoringMatrix toInternal(IOBlastxScoringMatrix val) {
    log.trace("#toInternal(IOBlastxScoringMatrix)");

    if (val == null) {
      return null;
    }

    return switch (val) {
      case BLOSUM45 -> BlastxScoringMatrix.Blosum45;
      case BLOSUM50 -> BlastxScoringMatrix.Blosum50;
      case BLOSUM62 -> BlastxScoringMatrix.Blosum62;
      case BLOSUM80 -> BlastxScoringMatrix.Blosum80;
      case BLOSUM90 -> BlastxScoringMatrix.Blosum90;
      case PAM30 -> BlastxScoringMatrix.Pam30;
      case PAM70 -> BlastxScoringMatrix.Pam70;
      case PAM250 -> BlastxScoringMatrix.Pam250;
    };
  }

  static BlastxTask toInternal(IOBlastxTask val) {
    log.trace("#toInternal(IOBlastxTask)");

    if (val == null)
      return null;

    return switch (val) {
      case BLASTX -> BlastxTask.BlastX;
      case BLASTXFAST -> BlastxTask.BlastXFast;
    };
  }

  static IOBlastxConfig toExternal(IOBlastxConfig out, BlastxConfig conf) {
    log.trace("#toExternal(IOBlastxConfig, BlastxConfig)");

    return getInstance().internalToExternal(out, conf);
  }

  static BlastxConfig toInternal(IOBlastxConfig conf) {
    log.trace("#toInternal(IOBlastxConfig)");

    return getInstance().externalToInternal(conf);
  }

  BlastxConfig externalToInternal(IOBlastxConfig conf) {
    log.trace("#externalToInternal(IOBlastxConfig)");

    if (conf == null)
      return null;

    return new BlastXConfigImpl()
      .setStrand(BCC.toInternal(conf.getStrand()))
      .setQueryTranslationGeneticCode(conf.getQueryGeneticCode())
      .setTask(toInternal(conf.getTask()))
      .setWordSize(conf.getWordSize())
      .setGapCostOpen(conf.getGapOpen())
      .setGapCostExtend(conf.getGapExtend())
      .setMaxIntronLength(conf.getMaxIntronLength())
      .setScoringMatrix(toInternal(conf.getMatrix()))
      .setScoreThreshold(conf.getThreshold())
      .setCompBasedStatisticsType(BCC.toInternal(conf.getCompBasedStats()))
      .setSeg(BCC.toInternal(conf.getSeg()))
      .enableSoftMasking(conf.getSoftMasking())
      .setTaxIDs(BCC.listToArray(conf.getTaxIds()))
      .setNegativeTaxIds(BCC.listToArray(conf.getNegativeTaxIds()))
      .setDbSoftMaskAlgorithmId(conf.getDbSoftMask())
      .setDbHardMaskAlgorithmId(conf.getDbHardMask())
      .setCullingLimit(conf.getCullingLimit())
      .setBestHitOverhang(conf.getBestHitOverhang())
      .setBestHitScoreEdge(conf.getBestHitScoreEdge())
      .enableSubjectBestHit(BCC.nullToFalse(conf.getSubjectBestHit()))
      .enableSumStatistics(conf.getSumStats())
      .setExtensionDropoffPreliminaryGapped(conf.getXDropGap())
      .setExtensionDropoffFinalGapped(conf.getXDropGapFinal())
      .setMultiHitWindowSize(conf.getWindowSize())
      .enableUngappedAlignmentOnly(BCC.nullToFalse(conf.getUngapped()))
      .enableSmithWatermanTraceback(BCC.nullToFalse(conf.getUseSWTraceback()));

  }

  IOBlastxConfig internalToExternal(IOBlastxConfig out, BlastxConfig conf) {
    log.trace("#intenralToExternal(IOBlastxConfig, BlastxConfig)");

    out.setStrand(BCC.toExternal(conf.getStrand()));
    out.setQueryGeneticCode(conf.getQueryTranslationGeneticCode());
    out.setTask(toExternal(conf.getTask()));
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapCostOpen());
    out.setGapExtend(conf.getGapCostExtend());
    out.setMaxIntronLength(conf.getMaxIntronLength());
    out.setMatrix(toExternal(conf.getScoringMatrix()));
    out.setThreshold(conf.getScoreThreshold());
    out.setCompBasedStats(BCC.toExternal(conf.getCompBasedStatisticsType()));
    out.setSeg(BCC.toExternal(conf.getSeg()));
    out.setSoftMasking(conf.isSoftMaskingEnabled());
    out.setTaxIds(BCC.arrayToList(conf.getTaxIds()));
    out.setNegativeTaxIds(BCC.arrayToList(conf.getNegativeTaxIds()));
    out.setDbSoftMask(conf.getDbSoftMaskAlgorithmId());
    out.setDbHardMask(conf.getDbHardMaskAlgorithmId());
    out.setCullingLimit(conf.getCullingLimit());
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.isSubjectBestHitEnabled());
    out.setSumStats(conf.isSumStatisticsEnabled());
    out.setXDropGap(conf.getExtensionDropoffPreliminaryGapped());
    out.setXDropGapFinal(conf.getExtensionDropoffFinalGapped());
    out.setWindowSize(conf.getMultiHitWindowSize());
    out.setUngapped(conf.isUngappedAlignmentOnlyEnabled());
    out.setUseSWTraceback(conf.isSmithWatermanTracebackEnabled());

    return out;
  }
}
