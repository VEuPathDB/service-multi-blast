package org.veupathdb.service.multiblast.service.valid;

import org.veupathdb.service.multiblast.generated.model.IOBlastxConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class BlastXValidator implements ConfigValidator<IOBlastxConfig>
{
  private static BlastXValidator instance;

  public static BlastXValidator getInstance() {
    if (instance == null) return instance = new BlastXValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(IOBlastxConfig conf) {
    var err = new ErrorMap();

    BlastValidator.validateGenCode(err, conf.getQueryGeneticCode(), QueryGeneticCode);
    Int.optGtEq(err, conf.getWordSize(), (byte) 2, WordSize);
    Int.optGtEq(err, conf.getMaxIntronLength(), 0, MaxIntronLength);
    Dec.optGtEq(err, conf.getThreshold(), 0, Threshold);
    SegValidator.getInstance()
      .validate(conf.getSeg())
      .forEach((k, v) -> err.putError(Seg, k, v));
    validateTaxIDs(err, conf);
    validateNegativeTaxIDs(err, conf);
    validateDbSoftMask(err, conf);
    validateDbHardMask(err, conf);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);
    Int.optGtEq(err, conf.getWindowSize(), 0, MultiHitWindowSize);

    return err;
  }

  static void validateBestHitScoreEdge(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateDbHardMask(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
    }
  }

  static void validateDbSoftMask(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
    }
  }

  static void validateNegativeTaxIDs(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getTaxIds(), NegativeTaxIDs, TaxIDs);
    }
  }

  static void validateTaxIDs(ErrorMap err, IOBlastxConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getNegativeTaxIds(), TaxIDs, NegativeTaxIDs);
    }
  }

}
