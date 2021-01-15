package org.veupathdb.service.multiblast.service.conv;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.IOBlastpConfig;
import org.veupathdb.service.multiblast.generated.model.IOBlastpScoringMatrix;
import org.veupathdb.service.multiblast.generated.model.IOBlastpTask;
import org.veupathdb.service.multiblast.model.blast.impl.BlastPConfigImpl;
import org.veupathdb.service.multiblast.model.blast.p.BlastpConfig;
import org.veupathdb.service.multiblast.model.blast.p.BlastpScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.p.BlastpTask;

class BlastpConverter
{
  private static final Logger log = LogProvider.logger(BlastpConverter.class);

  private static BlastpConverter instance;

  private BlastpConverter() {
    log.trace("#new()");
  }

  static BlastpConverter getInstance() {
    log.trace("#getInstance()");

    if (instance == null)
      return instance = new BlastpConverter();

    return instance;
  }

  static IOBlastpConfig toExternal(IOBlastpConfig out, BlastpConfig conf) {
    return getInstance().internalToExternal(out, conf);
  }

  static IOBlastpTask toExternal(BlastpTask val) {
    log.trace("#toExternal(BlastpTask)");

    if (val == null)
      return null;

    return switch (val) {
      case BlastP -> IOBlastpTask.BLASTP;
      case BlastPFast -> IOBlastpTask.BLASTPFAST;
      case BlastPShort -> IOBlastpTask.BLASTPSHORT;
    };
  }

  static IOBlastpScoringMatrix toExternal(BlastpScoringMatrix val) {
    log.trace("#toExternal(BlastpScoringMatrix)");

    if (val == null)
      return null;

    return switch (val) {
      case Blosum45 -> IOBlastpScoringMatrix.BLOSUM45;
      case Blosum50 -> IOBlastpScoringMatrix.BLOSUM50;
      case Blosum62 -> IOBlastpScoringMatrix.BLOSUM62;
      case Blosum80 -> IOBlastpScoringMatrix.BLOSUM80;
      case Blosum90 -> IOBlastpScoringMatrix.BLOSUM90;
      case Pam30 -> IOBlastpScoringMatrix.PAM30;
      case Pam70 -> IOBlastpScoringMatrix.PAM70;
      case Pam250 -> IOBlastpScoringMatrix.PAM250;
      case Identity -> IOBlastpScoringMatrix.IDENTITY;
    };
  }

  static BlastpTask toInternal(IOBlastpTask val) {
    log.trace("#toInternal(IOBlastpTask)");

    if (val == null)
      return null;

    return switch (val) {
      case BLASTP -> BlastpTask.BlastP;
      case BLASTPSHORT -> BlastpTask.BlastPShort;
      case BLASTPFAST -> BlastpTask.BlastPFast;
    };
  }

  static BlastpScoringMatrix toInternal(IOBlastpScoringMatrix val) {
    log.trace("#toInternal(IOBlastpScoringMatrix)");

    if (val == null)
      return null;

    return switch (val) {
      case BLOSUM45 -> BlastpScoringMatrix.Blosum45;
      case BLOSUM50 -> BlastpScoringMatrix.Blosum50;
      case BLOSUM62 -> BlastpScoringMatrix.Blosum62;
      case BLOSUM80 -> BlastpScoringMatrix.Blosum80;
      case BLOSUM90 -> BlastpScoringMatrix.Blosum90;
      case PAM30 -> BlastpScoringMatrix.Pam30;
      case PAM70 -> BlastpScoringMatrix.Pam70;
      case PAM250 -> BlastpScoringMatrix.Pam250;
      case IDENTITY -> BlastpScoringMatrix.Identity;
    };
  }

  static BlastpConfig toInternal(IOBlastpConfig conf) {
    log.trace("#toInternal(IOBlastpConfig)");

    return getInstance().externalToInternal(conf);
  }

  BlastpConfig externalToInternal(IOBlastpConfig conf) {
    log.trace("#externalToInternal(IOBlastpConfig)");

    return new BlastPConfigImpl()
      .setTask(toInternal(conf.getTask()))
      .setWordSize(conf.getWordSize())
      .setGapCostOpen(conf.getGapOpen())
      .setGapCostExtend(conf.getGapExtend())
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
      .setExtensionDropoffPreliminaryGapped(conf.getXDropGap())
      .setExtensionDropoffFinalGapped(conf.getXDropGapFinal())
      .enableUngappedAlignmentOnly(BCC.nullToFalse(conf.getUngapped()))
      .enableSmithWatermanTraceback(BCC.nullToFalse(conf.getUseSWTraceback()));
  }

  IOBlastpConfig internalToExternal(IOBlastpConfig out, BlastpConfig conf) {
    log.trace("#_fromInternal(BlastPConfig)");

    if (conf == null)
      return null;

    out.setTask(toExternal(conf.getTask()));
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapCostOpen());
    out.setGapExtend(conf.getGapCostExtend());
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
    out.setXDropGap(conf.getExtensionDropoffPreliminaryGapped());
    out.setXDropGapFinal(conf.getExtensionDropoffFinalGapped());
    out.setUngapped(conf.isUngappedAlignmentOnlyEnabled());
    out.setUseSWTraceback(conf.isSmithWatermanTracebackEnabled());

    return out;
  }
}
