package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.InputTBlastxConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class TBlastXValidator implements ConfigValidator<InputTBlastxConfig>
{
  private static TBlastXValidator instance;

  private final Logger log = LogProvider.logger(getClass());

  private TBlastXValidator() {
    log.trace("#new()");
  }

  public static TBlastXValidator getInstance() {
    if (instance == null) return instance = new TBlastXValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(InputTBlastxConfig conf) {
    log.trace("#validate(InputTBlastxConfig)");

    var err = new ErrorMap();

    BlastValidator.validateGenCode(err, conf.getQueryGeneticCode(), QueryGeneticCode);
    BlastValidator.validateGenCode(err, conf.getDbGencode(), DBGeneticCode);
    Int.optGtEq(err, conf.getWordSize(), (byte) 2, WordSize);
    Int.optGtEq(err, conf.getMaxIntronLength(), (byte) 0, MaxIntronLength);
    Dec.optGtEq(err, conf.getThreshold(), 0, Threshold);
    validateSubjectLoc(err, conf);
    SegValidator.getInstance()
      .validate(conf.getSeg())
      .forEach((k, v) -> err.putError(Seg, k, v));
    validateTaxIds(err, conf);
    validateNegativeTaxIds(err, conf);
    validateDbSoftMask(err, conf);
    validateDbHardMask(err, conf);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);
    Int.optGtEq(err, conf.getWindowSize(), 0, MultiHitWindowSize);

    return err;
  }

  static void validateSubjectLoc(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getSubjectLoc() != null) {
      Obj.colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      Obj.colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      Obj.incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      Obj.incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }

  static void validateNegativeTaxIds(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getNegativeTaxIds() == null || conf.getNegativeTaxIds().isEmpty())
      return;

    Obj.incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
  }

  static void validateTaxIds(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getTaxIds() == null || conf.getTaxIds().isEmpty())
      return;

    Obj.incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
  }

  static void validateBestHitScoreEdge(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateDbHardMask(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBHardMask, SubjectLocation);
    }
  }

  static void validateDbSoftMask(ErrorMap err, InputTBlastxConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
    }
  }

}
