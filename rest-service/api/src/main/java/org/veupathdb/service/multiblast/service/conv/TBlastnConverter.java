package org.veupathdb.service.multiblast.service.conv;

import org.veupathdb.service.multiblast.generated.model.IOTBlastnConfig;
import org.veupathdb.service.multiblast.model.blast.impl.TBlastNConfigImpl;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnConfig;

public class TBlastnConverter
{
  private static TBlastnConverter instance;

  public static TBlastnConverter getInstance() {
    if (instance == null)
      return instance = new TBlastnConverter();

    return instance;
  }

  static IOTBlastnConfig toExternal(IOTBlastnConfig out, TBlastnConfig conf) {
    return getInstance().internalToExternal(out, conf);
  }

  static TBlastnConfig toInternal(IOTBlastnConfig val) {
    return getInstance().externalToInternal(val);
  }

  IOTBlastnConfig internalToExternal(IOTBlastnConfig out, TBlastnConfig conf) {
    if (conf == null)
      return null;

    out.setTask(conf.getTask());
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapCostOpen());
    out.setGapExtend(conf.getGapCostExtend());
    out.setDbGencode(conf.getDbTranslationGeneticCode());
    out.setMaxIntronLength(conf.getMaxIntronLength());
    out.setMatrix(conf.getScoringMatrix());
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
    if (val == null)
      return null;

    return new TBlastNConfigImpl()
      .setTask(val.getTask())
      .setWordSize(val.getWordSize())
      .setGapCostOpen(val.getGapOpen())
      .setGapCostExtend(val.getGapExtend())
      .setDbTranslationGeneticCode(val.getDbGencode())
      .setMaxIntronLength(val.getMaxIntronLength())
      .setScoringMatrix(val.getMatrix())
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
