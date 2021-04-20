package mb.api.service.conv;

import mb.api.model.blast.IOTBlastxConfig;
import mb.api.model.blast.IOTBlastxScoringMatrix;
import mb.lib.blast.model.impl.TBlastXConfigImpl;
import mb.lib.blast.model.tx.TBlastXConfig;
import mb.lib.blast.model.tx.TBlastxScoringMatrix;

class TBlastxConverter
{
  private static TBlastxConverter instance;

  public static TBlastxConverter getInstance() {
    if (instance == null)
      return instance = new TBlastxConverter();

    return instance;
  }

  static IOTBlastxConfig toExternal(IOTBlastxConfig out, TBlastXConfig conf) {
    return getInstance().internalToExternal(out, conf);
  }

  static IOTBlastxScoringMatrix toExternal(TBlastxScoringMatrix val) {
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
    return getInstance().externalToInternal(conf);
  }

  TBlastXConfig externalToInternal(IOTBlastxConfig conf) {
    return new TBlastXConfigImpl()
      .setStrand(conf.getStrand())
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
    out.setStrand(conf.getStrand());
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
