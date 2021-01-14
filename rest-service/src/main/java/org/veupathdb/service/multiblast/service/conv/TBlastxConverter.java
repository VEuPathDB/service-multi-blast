package org.veupathdb.service.multiblast.service.conv;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.IOTBlastxConfig;
import org.veupathdb.service.multiblast.generated.model.IOTBlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastXConfig;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.impl.TBlastXConfigImpl;

class TBlastxConverter
{
  private static final Logger           log = LogProvider.logger(TBlastxConverter.class);
  private static       TBlastxConverter instance;

  private TBlastxConverter() {
    log.trace("#new()");
  }

  public static TBlastxConverter getInstance() {
    log.trace("#getInstance()");

    if (instance == null)
      return instance = new TBlastxConverter();

    return instance;
  }

  static IOTBlastxConfig toExternal(IOTBlastxConfig out, TBlastXConfig conf) {
    log.trace("#toExternal(IOTBlastxConfig, TBlastxConfig)");

    return getInstance().internalToExternal(out, conf);
  }

  static IOTBlastxScoringMatrix toExternal(TBlastxScoringMatrix val) {
    log.trace("#toExternal(TBlastxScoringMatrix)");

    if (val == null)
      return null;

    return switch (val) {
      case Blosum45 -> IOTBlastxScoringMatrix.BLOSUM45;
      case Blosum50 -> IOTBlastxScoringMatrix.BLOSUM50;
      case Blosum62 -> IOTBlastxScoringMatrix.BLOSUM62;
      case Blosum80 -> IOTBlastxScoringMatrix.BLOSUM80;
      case Blosum90 -> IOTBlastxScoringMatrix.BLOSUM90;
      case Pam30 -> IOTBlastxScoringMatrix.PAM30;
      case Pam70 -> IOTBlastxScoringMatrix.PAM70;
      case Pam250 -> IOTBlastxScoringMatrix.PAM250;
    };
  }

  static TBlastxScoringMatrix toInternal(IOTBlastxScoringMatrix val) {
    log.trace("#toInternal(IOTBlastxScoringMatrix)");

    if (val == null)
      return null;

    return switch (val) {
      case BLOSUM45 -> TBlastxScoringMatrix.Blosum45;
      case BLOSUM50 -> TBlastxScoringMatrix.Blosum50;
      case BLOSUM62 -> TBlastxScoringMatrix.Blosum62;
      case BLOSUM80 -> TBlastxScoringMatrix.Blosum80;
      case BLOSUM90 -> TBlastxScoringMatrix.Blosum90;
      case PAM250 -> TBlastxScoringMatrix.Pam250;
      case PAM30 -> TBlastxScoringMatrix.Pam30;
      case PAM70 -> TBlastxScoringMatrix.Pam70;
    };
  }

  static TBlastXConfig toInternal(IOTBlastxConfig conf) {
    log.trace("#toInternal(IOTBlastxConfig)");

    return getInstance().externalToInternal(conf);
  }

  TBlastXConfig externalToInternal(IOTBlastxConfig conf) {
    log.trace("#externalToInternal(IOTBlastxConfig)");

    return new TBlastXConfigImpl()
      .setStrand(BCC.toInternal(conf.getStrand()))
      .setQueryTranslationGeneticCode(conf.getQueryGeneticCode())
      .setWordSize(conf.getWordSize())
      .setMaxIntronLength(conf.getMaxIntronLength())
      .setScoringMatrix(toInternal(conf.getMatrix()))
      .setScoreThreshold(conf.getThreshold())
      .setDbTranslationGeneticCode(conf.getDbGencode())
      .setSeg(BCC.toInternal(conf.getSeg()))
      .enableSoftMasking(conf.getSoftMasking())
      .setTaxIDs(conf.getTaxIds())
      .setNegativeTaxIds(conf.getNegativeTaxIds())
      .setDbSoftMaskAlgorithmId(conf.getDbSoftMask())
      .setDbHardMaskAlgorithmId(conf.getDbHardMask())
      .setCullingLimit(conf.getCullingLimit())
      .setBestHitOverhang(conf.getBestHitOverhang())
      .setBestHitScoreEdge(conf.getBestHitScoreEdge())
      .enableSubjectBestHit(BCC.nullToFalse(conf.getSubjectBestHit()))
      .enableSumStatistics(conf.getSumStats())
      .setMultiHitWindowSize(conf.getWindowSize());
  }

  IOTBlastxConfig internalToExternal(IOTBlastxConfig out, TBlastXConfig conf) {
    log.trace("#internalToExternal(IOTBlastxConfig, TBlastxConfig)");

    out.setStrand(BCC.toExternal(conf.getStrand()));
    out.setQueryGeneticCode(conf.getQueryTranslationGeneticCode());
    out.setWordSize(conf.getWordSize());
    out.setMaxIntronLength(conf.getMaxIntronLength());
    out.setMatrix(toExternal(conf.getScoringMatrix()));
    out.setThreshold(conf.getScoreThreshold());
    out.setDbGencode(conf.getQueryTranslationGeneticCode());
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
    out.setWindowSize(conf.getMultiHitWindowSize());

    return out;
  }
}
