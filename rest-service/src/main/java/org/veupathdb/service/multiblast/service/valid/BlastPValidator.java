package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.InputBlastpConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastpTask;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class BlastPValidator implements ConfigValidator<InputBlastpConfig>
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Management                                              ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static BlastPValidator instance;

  private final Logger log = LogManager.getLogger(getClass());

  public static BlastPValidator getInstance() {
    if (instance == null)
      instance = new BlastPValidator();

    return instance;
  }

  public ErrorMap validate(InputBlastpConfig conf) {
    log.trace("#validateConfig(ErrorMap, InputBlastpConfig)");

    var err = new ErrorMap();

    Int.optGtEq(err, conf.getWordSize(), 2, JsonKeys.WordSize);
    validateGapOpen(err, conf);
    validateGapExtend(err, conf);
    validateMatrix(err, conf);
    Dec.optGtEq(err, conf.getThreshold(), 0, JsonKeys.Threshold);
    validateSubjectLoc(err, conf);
    validateSoftMasking(err, conf);
    validateTaxIds(err, conf);
    validateNegativeTaxIds(err, conf);
    validateDbSoftMask(err, conf);
    validateDbHardMask(err, conf);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);

    return err;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Methods                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateBestHitScoreEdge(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateDbHardMask(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBHardMask, SubjectLocation);
    }
  }

  static void validateDbSoftMask(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
      Obj.incompat(err, conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
    }
  }

  static void validateNegativeTaxIds(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getNegativeTaxIds() == null || conf.getNegativeTaxIds().isEmpty())
      return;

    Obj.incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
  }

  static void validateTaxIds(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getTaxIds() == null || conf.getTaxIds().isEmpty())
      return;

    Obj.incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
  }

  static void validateSoftMasking(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getSoftMasking() == null || !conf.getSoftMasking())
      return;

    if (conf.getTask() != InputBlastpTask.BLASTP)
      err.putError(
        SoftMasking,
        String.format(BlastValidator.errOnlyTask, InputBlastpTask.BLASTP.name)
      );
  }

  static void validateSubjectLoc(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getSubjectLoc() != null) {
      Obj.colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      Obj.colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      Obj.incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      Obj.incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }

  static void validateMatrix(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getMatrix() == null)
      return;

    if (conf.getTask() == InputBlastpTask.BLASTPFAST)
      err.putError(
        JsonKeys.Matrix,
        String.format(BlastValidator.errNotTask, InputBlastpTask.BLASTPFAST.name)
      );
  }

  static void validateGapExtend(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getGapExtend() == null)
      return;

    if (conf.getTask() == InputBlastpTask.BLASTPFAST)
      err.putError(
        JsonKeys.GapExtend,
        String.format(BlastValidator.errNotTask, InputBlastpTask.BLASTPFAST.name)
      );
  }

  static void validateGapOpen(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getGapOpen() == null)
      return;

    if (conf.getTask() == InputBlastpTask.BLASTPFAST)
      err.putError(
        JsonKeys.GapOpen,
        String.format(BlastValidator.errNotTask, InputBlastpTask.BLASTPFAST.name)
      );
  }
}
