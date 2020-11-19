package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.InputTBlastnConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class TBlastNValidator implements ConfigValidator<InputTBlastnConfig>
{
  private static TBlastNValidator instance;

  private Logger log = LogProvider.logger(getClass());

  private TBlastNValidator() {
    log.trace("#new()");
  }

  public static TBlastNValidator getInstance() {
    if (instance == null) return instance = new TBlastNValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(InputTBlastnConfig conf) {
    log.trace("#validate(InputTBlastnConfig)");

    var err = new ErrorMap();

    Int.optGtEq(err, conf.getWordSize(), 2, JsonKeys.WordSize);
    BlastValidator.validateGenCode(err, conf.getDbGencode(), DBGeneticCode);
    Int.optGtEq(err, conf.getMaxIntronLength(), 0, MaxIntronLength);
    Dec.optGtEq(err, conf.getThreshold(), 0, Threshold);
    validateSubjectLoc(err, conf);
    SegValidator.getInstance()
      .validate(conf.getSeg())
      .forEach((k, v) -> err.putError(Seg, k, v));
    validateTaxIDs(err, conf);
    validateNegativeTaxIDs(err, conf);
    validateDBSoftMask(err, conf);
    validateDBHardMask(err, conf);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);

    return err;
  }

  private void validateBestHitScoreEdge(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  private void validateBestHitOverhang(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  private void validateCullingLimit(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  private void validateDBHardMask(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getSubjectLoc(), DBHardMask, SubjectLocation);
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
    }
  }

  private void validateDBSoftMask(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
    }
  }

  private void validateNegativeTaxIDs(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getTaxIds(), NegativeTaxIDs, TaxIDs);
      Obj.incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
    }
  }

  private void validateTaxIDs(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getNegativeTaxIds(), TaxIDs, NegativeTaxIDs);
      Obj.incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
    }
  }

  private void validateSubjectLoc(ErrorMap err, InputTBlastnConfig conf) {
    if (conf.getSubjectLoc() != null) {
      Obj.colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      Obj.colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      Obj.incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      Obj.incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }
}
