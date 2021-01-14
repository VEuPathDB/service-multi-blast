package org.veupathdb.service.multiblast.service.conv;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.IOTBlastnConfig;
import org.veupathdb.service.multiblast.generated.model.IOTBlastnScoringMatrix;
import org.veupathdb.service.multiblast.generated.model.IOTBlastnTask;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnConfig;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastNScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastNTask;
import org.veupathdb.service.multiblast.model.blast.impl.TBlastNConfigImpl;

public class TBlastnConverter
{
  private static final Logger log = LogProvider.logger(TBlastnConverter.class);

  private static TBlastnConverter instance;

  private TBlastnConverter() {
    log.trace("#new()");
  }

  public static TBlastnConverter getInstance() {
    log.trace("#getInstance()");

    if (instance == null)
      return instance = new TBlastnConverter();

    return instance;
  }

  static IOTBlastnTask toExternal(TBlastNTask task) {
    log.trace("#toExternal(TBlastnTask)");

    if (task == null)
      return null;

    return switch (task) {
      case TBlastN -> IOTBlastnTask.TBLASTN;
      case TBlastNFast -> IOTBlastnTask.TBLASTNFAST;
    };
  }

  static IOTBlastnScoringMatrix toExternal(TBlastNScoringMatrix matrix) {
    log.trace("#toExternal(TBlastnScoringMatrix)");

    if (matrix == null)
      return null;

    return switch (matrix) {
      case Blosum45 -> IOTBlastnScoringMatrix.BLOSUM45;
      case Blosum50 -> IOTBlastnScoringMatrix.BLOSUM50;
      case Blosum62 -> IOTBlastnScoringMatrix.BLOSUM62;
      case Blosum80 -> IOTBlastnScoringMatrix.BLOSUM80;
      case Blosum90 -> IOTBlastnScoringMatrix.BLOSUM90;
      case Pam30 -> IOTBlastnScoringMatrix.PAM30;
      case Pam70 -> IOTBlastnScoringMatrix.PAM70;
      case Pam250 -> IOTBlastnScoringMatrix.PAM250;
      case Identity -> IOTBlastnScoringMatrix.IDENTITY;
    };
  }

  static IOTBlastnConfig toExternal(IOTBlastnConfig out, TBlastnConfig conf) {
    log.trace("#toExternal(IOTBlastnConfig, TBlastnConfig)");

    return getInstance().internalToExternal(out, conf);
  }

  static TBlastNTask toInternal(IOTBlastnTask val) {
    log.trace("#toInternal(IOTBlastnTask)");

    if (val == null)
      return null;

    return switch (val) {
      case TBLASTN -> TBlastNTask.TBlastN;
      case TBLASTNFAST -> TBlastNTask.TBlastNFast;
    };
  }

  static TBlastNScoringMatrix toInternal(IOTBlastnScoringMatrix val) {
    log.trace("#toInternal(IOTBlastnScoringMatrix)");

    if (val == null)
      return null;

    return switch (val) {
      case BLOSUM45 -> TBlastNScoringMatrix.Blosum45;
      case BLOSUM50 -> TBlastNScoringMatrix.Blosum50;
      case BLOSUM62 -> TBlastNScoringMatrix.Blosum62;
      case BLOSUM80 -> TBlastNScoringMatrix.Blosum80;
      case BLOSUM90 -> TBlastNScoringMatrix.Blosum90;
      case PAM30 -> TBlastNScoringMatrix.Pam30;
      case PAM70 -> TBlastNScoringMatrix.Pam70;
      case PAM250 -> TBlastNScoringMatrix.Pam250;
      case IDENTITY -> TBlastNScoringMatrix.Identity;
    };
  }

  static TBlastnConfig toInternal(IOTBlastnConfig val) {
    log.trace("#toInternal(IOTBlastnConfig)");

    return getInstance().externalToInternal(val);
  }

  IOTBlastnConfig internalToExternal(IOTBlastnConfig out, TBlastnConfig conf) {
    log.trace("#internalToExternal(IOTBlastnConfig, TBlastNConfig)");

    if (conf == null)
      return null;

    out.setTask(toExternal(conf.getTask()));
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapCostOpen());
    out.setGapExtend(conf.getGapCostExtend());
    out.setDbGencode(conf.getDbTranslationGeneticCode());
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
    out.setUngapped(conf.isUngappedAlignmentOnlyEnabled());
    out.setWindowSize(conf.getMultiHitWindowSize());
    out.setUseSWTraceback(conf.isSmithWatermanTracebackEnabled());

    return out;
  }

  private TBlastnConfig externalToInternal(IOTBlastnConfig val) {
    log.trace("#exernalToInternal(IOTBlastnConfig)");

    if (val == null)
      return null;

    return new TBlastNConfigImpl()
      .setTask(toInternal(val.getTask()))
      .setWordSize(val.getWordSize())
      .setGapCostOpen(val.getGapOpen())
      .setGapCostExtend(val.getGapExtend())
      .setDbTranslationGeneticCode(val.getDbGencode())
      .setMaxIntronLength(val.getMaxIntronLength())
      .setScoringMatrix(toInternal(val.getMatrix()))
      .setScoreThreshold(val.getThreshold())
      .setCompBasedStatisticsType(BCC.toInternal(val.getCompBasedStats()))
      .setSeg(BCC.toInternal(val.getSeg()))
      .enableSoftMasking(val.getSoftMasking())
      .setTaxIDs(val.getTaxIds())
      .setNegativeTaxIds(val.getNegativeTaxIds())
      .setDbSoftMaskAlgorithmId(val.getDbSoftMask())
      .setDbHardMaskAlgorithmId(val.getDbHardMask())
      .setCullingLimit(val.getCullingLimit())
      .setBestHitOverhang(val.getBestHitOverhang())
      .setBestHitScoreEdge(val.getBestHitScoreEdge())
      .enableSubjectBestHit(BCC.nullToFalse(val.getSubjectBestHit()))
      .enableSumStatistics(val.getSumStats())
      .setExtensionDropoffPreliminaryGapped(val.getXDropGap())
      .setExtensionDropoffFinalGapped(val.getXDropGapFinal())
      .enableUngappedAlignmentOnly(BCC.nullToFalse(val.getUngapped()))
      .setMultiHitWindowSize(val.getWindowSize())
      .enableSmithWatermanTraceback(BCC.nullToFalse(val.getUseSWTraceback()));
  }
}
