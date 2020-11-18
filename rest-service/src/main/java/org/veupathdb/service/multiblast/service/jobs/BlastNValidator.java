package org.veupathdb.service.multiblast.service.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.InputBlastnConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastnDcTemplateType;
import org.veupathdb.service.multiblast.generated.model.InputBlastnTask;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastnTask;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

public class BlastNValidator extends BlastValidator
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

  public ErrorMap validateConfig(ErrorMap err, InputBlastnConfig conf) {
    log.trace("#validateConfig(ErrorMap, InputBlastnConfig)");

    optGtEq(err, conf.getWordSize(), 4, JsonKeys.WordSize);
    optLtEq(err, conf.getPenalty(), 0, JsonKeys.Penalty);
    optGtEq(err, conf.getReward(), 0, JsonKeys.Reward);
    validateSubjectLoc(err, conf);
    validateTaxIds(err, conf);
    validateNegativeTaxIds(err, conf);
    validateDbSoftMask(err, conf);
    validateDbHardMask(err, conf);
    optBetweenInc(err, conf.getPercIdentity(), 0, 100, JsonKeys.PercentIdentity);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);
    validateTemplateType(err, conf);
    validateTemplateLength(err, conf);
    validateNoGreedy(err, conf);
    validateWindowSize(err, conf);
    optGtEq(err, conf.getOffDiagonalRange(), 0, JsonKeys.OffDiagonalRange);

    return err;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Methods                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private void validateBestHitScoreEdge(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      betweenExc(err, conf.getBestHitScoreEdge(), 0.0, 0.5, BestHitScoreEdge);
      incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  private void validateBestHitOverhang(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      betweenExc(err, conf.getBestHitOverhang(), 0.0, 0.5, BestHitOverhang);
      incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  private void validateCullingLimit(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getCullingLimit() != null) {
      gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateNoGreedy(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getNoGreedy() != null && conf.getTask() != InputBlastnTask.MEGABLAST)
      err.putError(NonGreedy, String.format(errOnlyTask, BlastnTask.Megablast));
  }

  static void validateTemplateLength(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getTemplateLength() == null)
      return;

    if (conf.getTemplateType() == null)
      conf.setTemplateType(InputBlastnDcTemplateType.CODING);

    if (conf.getTask() != InputBlastnTask.DCMEGABLAST)
      err.putError(TemplateLength, String.format(errOnlyTask, BlastnTask.DiscontiguousMegablast));

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
      err.putError(TemplateType, String.format(errOnlyTask, BlastnTask.DiscontiguousMegablast));
  }

  static void validateDbHardMask(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getDbHardMask() != null) {
      incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
      incompat(err, conf.getSubjectLoc(), DBHardMask, SubjectLocation);
    }
  }

  static void validateDbSoftMask(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getDbSoftMask() != null) {
      incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
      incompat(err, conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
    }
  }

  static void validateTaxIds(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty()) {
      colIncompat(err, conf.getNegativeTaxIds(), TaxIDs, NegativeTaxIDs);
      incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
    }
  }

  static void validateNegativeTaxIds(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty()) {
      colIncompat(err, conf.getTaxIds(), NegativeTaxIDs, TaxIDs);
      incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
    }
  }

  static void validateSubjectLoc(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getSubjectLoc() != null) {
      colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }

  static void validateWindowSize(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getWindowSize() == null)
      return;

    gtEq(err, conf.getWindowSize(), 0, MultiHitWindowSize);

    if (conf.getTask() != InputBlastnTask.DCMEGABLAST)
      err.putError(
        MultiHitWindowSize,
        String.format(errOnlyTask, BlastnTask.DiscontiguousMegablast)
      );
  }
}
