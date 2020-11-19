package org.veupathdb.service.multiblast.service.valid;

import org.veupathdb.service.multiblast.generated.model.InputBlastxConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class BlastXValidator implements ConfigValidator<InputBlastxConfig>
{
  private static BlastXValidator instance;

  public static BlastXValidator getInstance() {
    if (instance == null) return instance = new BlastXValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(InputBlastxConfig conf) {
    var err = new ErrorMap();

    BlastValidator.validateGenCode(err, conf.getQueryGeneticCode(), QueryGeneticCode);
    Int.optGtEq(err, conf.getWordSize(), (byte) 2, WordSize);
    Int.optGtEq(err, conf.getMaxIntronLength(), 0, MaxIntronLength);
    Dec.optGtEq(err, conf.getThreshold(), 0, Threshold);
    validateSubjectLoc(err, conf);
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

  private void validateBestHitScoreEdge(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  private void validateBestHitOverhang(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  private void validateCullingLimit(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  private void validateDbHardMask(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBHardMask, SubjectLocation);
    }
  }

  private void validateDbSoftMask(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
    }
  }

  private void validateNegativeTaxIDs(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getTaxIds(), NegativeTaxIDs, TaxIDs);
      Obj.incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
    }
  }

  private void validateTaxIDs(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getNegativeTaxIds(), TaxIDs, NegativeTaxIDs);
      Obj.incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
    }
  }

  private void validateSubjectLoc(ErrorMap err, InputBlastxConfig conf) {
    if (conf.getSubjectLoc() != null) {
      Obj.colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      Obj.colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      Obj.incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      Obj.incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }

}
