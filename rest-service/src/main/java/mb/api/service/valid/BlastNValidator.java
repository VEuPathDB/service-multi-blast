package mb.api.service.valid;

import mb.api.model.blast.IOBlastnConfig;
import mb.api.model.io.JsonKeys;
import mb.api.service.model.ErrorMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.field.BlastNTask;
import org.veupathdb.lib.blast.field.TemplateType;

/**
 * Validator for {@code blastn} CLI arguments.
 */
class BlastNValidator implements ConfigValidator<IOBlastnConfig>
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Management                                              ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static BlastNValidator instance;

  private final Logger log = LogManager.getLogger(getClass());

  public static BlastNValidator getInstance() {
    if (instance == null)
      instance = new BlastNValidator();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Public API                                                       ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  @Override
  public ErrorMap validate(IOBlastnConfig conf) {
    log.trace("BlastNValidator#validateConfig(ErrorMap, InputBlastnConfig)");

    var err = new ErrorMap();

    Int.optGtEq(err, conf.getWordSize(), 4, JsonKeys.WordSize);
    Int.optLtEq(err, conf.getPenalty(), 0, JsonKeys.Penalty);
    Int.optGtEq(err, conf.getReward(), 0, JsonKeys.Reward);
    validateTaxIds(err, conf);
    validateNegativeTaxIds(err, conf);
    validateDbSoftMask(err, conf);
    validateDbHardMask(err, conf);
    Dec.optBetweenInc(err, conf.getPercIdentity(), 0, 100, JsonKeys.PercentIdentity);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);
    validateTemplateType(err, conf);
    validateTemplateLength(err, conf);
    validateNoGreedy(err, conf);
    validateWindowSize(err, conf);
    Int.optGtEq(err, conf.getOffDiagonalRange(), 0, JsonKeys.OffDiagonalRange);

    return err;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Methods                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateBestHitScoreEdge(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0.0, 0.5, JsonKeys.BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), JsonKeys.BestHitScoreEdge, JsonKeys.CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0.0, 0.5, JsonKeys.BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), JsonKeys.BestHitOverhang, JsonKeys.CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, JsonKeys.CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), JsonKeys.CullingLimit, JsonKeys.BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), JsonKeys.CullingLimit, JsonKeys.BestHitScoreEdge);
    }
  }

  static void validateNoGreedy(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getNoGreedy() != null && conf.getTask() != BlastNTask.Megablast)
      err.putError(JsonKeys.NonGreedy, String.format(BlastValidator.errOnlyTask, BlastNTask.Megablast));
  }

  static void validateTemplateLength(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getTemplateLength() == null)
      return;

    if (conf.getTemplateType() == null)
      conf.setTemplateType(TemplateType.Coding);

    if (conf.getTask() != BlastNTask.DiscontiguousMegablast)
      err.putError(JsonKeys.TemplateLength, String.format(BlastValidator.errOnlyTask, BlastNTask.DiscontiguousMegablast));

    if (
      conf.getTemplateLength() != 16
        && conf.getTemplateLength() != 18
        && conf.getTemplateLength() != 21
    )
      err.putError(JsonKeys.TemplateLength, "must be one of 16, 18, or 21");
  }

  static void validateTemplateType(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getTemplateType() == null)
      return;

    if (conf.getTemplateLength() == null)
      conf.setTemplateLength((byte) 18);

    if (conf.getTask() != BlastNTask.DiscontiguousMegablast)
      err.putError(JsonKeys.TemplateType, String.format(BlastValidator.errOnlyTask, BlastNTask.DiscontiguousMegablast));
  }

  static void validateDbHardMask(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), JsonKeys.DBHardMask, JsonKeys.DBSoftMask);
    }
  }

  static void validateDbSoftMask(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), JsonKeys.DBSoftMask, JsonKeys.DBHardMask);
    }
  }

  static void validateTaxIds(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getNegativeTaxIds(), JsonKeys.TaxIDs, JsonKeys.NegativeTaxIDs);
    }
  }

  static void validateNegativeTaxIds(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getTaxIds(), JsonKeys.NegativeTaxIDs, JsonKeys.TaxIDs);
    }
  }

  static void validateWindowSize(ErrorMap err, IOBlastnConfig conf) {
    if (conf.getWindowSize() == null)
      return;

    Int.gtEq(err, conf.getWindowSize(), 0, JsonKeys.MultiHitWindowSize);

    if (conf.getTask() != BlastNTask.DiscontiguousMegablast)
      err.putError(
        JsonKeys.MultiHitWindowSize,
        String.format(BlastValidator.errOnlyTask, BlastNTask.DiscontiguousMegablast)
      );
  }
}
