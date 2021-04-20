package mb.api.service.conv;

import mb.api.model.blast.IOBlastpConfig;
import mb.api.model.blast.IOBlastpScoringMatrix;
import mb.lib.blast.model.impl.BlastPConfigImpl;
import mb.lib.blast.model.p.BlastpConfig;
import mb.lib.blast.model.p.BlastpScoringMatrix;

class BlastpConverter
{
  private static BlastpConverter instance;

  static BlastpConverter getInstance() {
    if (instance == null)
      return instance = new BlastpConverter();

    return instance;
  }

  static IOBlastpConfig toExternal(IOBlastpConfig out, BlastpConfig conf) {
    return getInstance().internalToExternal(out, conf);
  }

  static IOBlastpScoringMatrix toExternal(BlastpScoringMatrix val) {
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

  static BlastpScoringMatrix toInternal(IOBlastpScoringMatrix val) {
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
    return getInstance().externalToInternal(conf);
  }

  BlastpConfig externalToInternal(IOBlastpConfig conf) {
    return new BlastPConfigImpl()
      .setTask(conf.getTask())
      .setWordSize(conf.getWordSize())
      .setGapCostOpen(conf.getGapOpen())
      .setGapCostExtend(conf.getGapExtend())
      .setScoringMatrix(toInternal(conf.getMatrix()))
      .setScoreThreshold(conf.getThreshold())
      .setCompBasedStatisticsType(conf.getCompBasedStats())
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
    if (conf == null)
      return null;

    out.setTask(conf.getTask());
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapCostOpen());
    out.setGapExtend(conf.getGapCostExtend());
    out.setMatrix(toExternal(conf.getScoringMatrix()));
    out.setThreshold(conf.getScoreThreshold());
    out.setCompBasedStats(conf.getCompBasedStatisticsType());
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
