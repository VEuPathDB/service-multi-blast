package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.InputBlastnConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastnDcTemplateType;
import org.veupathdb.service.multiblast.generated.model.InputBlastnTask;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastnTask;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class BlastNValidator implements ConfigValidator<InputBlastnConfig>
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
  public ErrorMap validate(InputBlastnConfig conf) {
    log.trace("#validateConfig(ErrorMap, InputBlastnConfig)");

    var err = new ErrorMap();

    Int.optGtEq(err, conf.getWordSize(), 4, JsonKeys.WordSize);
    Int.optLtEq(err, conf.getPenalty(), 0, JsonKeys.Penalty);
    Int.optGtEq(err, conf.getReward(), 0, JsonKeys.Reward);
    validateSubjectLoc(err, conf);
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

  private void validateBestHitScoreEdge(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0.0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  private void validateBestHitOverhang(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0.0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  private void validateCullingLimit(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateNoGreedy(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getNoGreedy() != null && conf.getTask() != InputBlastnTask.MEGABLAST)
      err.putError(NonGreedy, String.format(BlastValidator.errOnlyTask, BlastnTask.Megablast));
  }

  static void validateTemplateLength(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getTemplateLength() == null)
      return;

    if (conf.getTemplateType() == null)
      conf.setTemplateType(InputBlastnDcTemplateType.CODING);

    if (conf.getTask() != InputBlastnTask.DCMEGABLAST)
      err.putError(TemplateLength, String.format(BlastValidator.errOnlyTask, BlastnTask.DiscontiguousMegablast));

    if (
      conf.getTemplateLength() != 16
        && conf.getTemplateLength() != 18
        && conf.getTemplateLength() != 21
    )
      err.putError(TemplateLength, "must be one of 16, 18, or 21");
  }

  static void validateTemplateType(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getTemplateType() == null)
      return;

    if (conf.getTemplateLength() == null)
      conf.setTemplateLength((byte) 18);

    if (conf.getTask() != InputBlastnTask.DCMEGABLAST)
      err.putError(TemplateType, String.format(BlastValidator.errOnlyTask, BlastnTask.DiscontiguousMegablast));
  }

  static void validateDbHardMask(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBHardMask, SubjectLocation);
    }
  }

  static void validateDbSoftMask(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
    }
  }

  static void validateTaxIds(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getNegativeTaxIds(), TaxIDs, NegativeTaxIDs);
      Obj.incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
    }
  }

  static void validateNegativeTaxIds(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      Obj.colIncompat(err, conf.getTaxIds(), NegativeTaxIDs, TaxIDs);
      Obj.incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
    }
  }

  static void validateSubjectLoc(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getSubjectLoc() != null) {
      Obj.colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      Obj.colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      Obj.incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      Obj.incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }

  static void validateWindowSize(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getWindowSize() == null)
      return;

    Int.gtEq(err, conf.getWindowSize(), 0, MultiHitWindowSize);

    if (conf.getTask() != InputBlastnTask.DCMEGABLAST)
      err.putError(
        MultiHitWindowSize,
        String.format(BlastValidator.errOnlyTask, BlastnTask.DiscontiguousMegablast)
      );
  }
}
