package mb.api.service.valid;

import mb.api.model.blast.IOTBlastnConfig;
import mb.api.model.io.JsonKeys;
import mb.api.service.model.ErrorMap;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;

import static mb.api.model.io.JsonKeys.*;

/**
 * Validator for {@code tblastn} CLI arguments.
 */
class TBlastNValidator implements ConfigValidator<IOTBlastnConfig>
{
  private static TBlastNValidator instance;

  private final Logger log = LogProvider.logger(getClass());

  private TBlastNValidator() {
    log.trace("TBlastNValidator#new()");
  }

  public static TBlastNValidator getInstance() {
    if (instance == null) return instance = new TBlastNValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(IOTBlastnConfig conf) {
    log.trace("TBlastNValidator#validate(IOTBlastnConfig)");

    var err = new ErrorMap();

    Int.optGtEq(err, conf.getWordSize(), 2, JsonKeys.WordSize);
    BlastValidator.validateGenCode(err, conf.getDbGencode(), DBGeneticCode);
    Int.optGtEq(err, conf.getMaxIntronLength(), 0, MaxIntronLength);
    Dec.optGtEq(err, conf.getThreshold(), 0, Threshold);
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

  static void validateBestHitScoreEdge(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateDBHardMask(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
    }
  }

  static void validateDBSoftMask(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
    }
  }

  static void validateNegativeTaxIDs(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getTaxIds(), NegativeTaxIDs, TaxIDs);
    }
  }

  static void validateTaxIDs(ErrorMap err, IOTBlastnConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getNegativeTaxIds(), TaxIDs, NegativeTaxIDs);
    }
  }
}
