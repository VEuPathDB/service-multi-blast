package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.IOBlastpConfig;
import org.veupathdb.service.multiblast.generated.model.IOBlastpTask;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

class BlastPValidator implements ConfigValidator<IOBlastpConfig>
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

  public ErrorMap validate(IOBlastpConfig conf) {
    log.trace("BlastPValidator#validateConfig(ErrorMap, InputBlastpConfig)");

    var err = new ErrorMap();

    Int.optGtEq(err, conf.getWordSize(), 2, JsonKeys.WordSize);
    validateGapOpen(err, conf);
    validateGapExtend(err, conf);
    validateMatrix(err, conf);
    Dec.optGtEq(err, conf.getThreshold(), 0, JsonKeys.Threshold);
    validateSoftMasking(err, conf);
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

  static void validateBestHitScoreEdge(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateDbHardMask(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
    }
  }

  static void validateDbSoftMask(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
    }
  }


  static void validateSoftMasking(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getSoftMasking() == null || !conf.getSoftMasking())
      return;

    if (conf.getTask() != IOBlastpTask.BLASTP)
      err.putError(
        SoftMasking,
        String.format(BlastValidator.errOnlyTask, IOBlastpTask.BLASTP.name)
      );
  }

  static void validateMatrix(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getMatrix() == null)
      return;

    if (conf.getTask() == IOBlastpTask.BLASTPFAST)
      err.putError(
        JsonKeys.Matrix,
        String.format(BlastValidator.errNotTask, IOBlastpTask.BLASTPFAST.name)
      );
  }

  static void validateGapExtend(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getGapExtend() == null)
      return;

    if (conf.getTask() == IOBlastpTask.BLASTPFAST)
      err.putError(
        JsonKeys.GapExtend,
        String.format(BlastValidator.errNotTask, IOBlastpTask.BLASTPFAST.name)
      );
  }

  static void validateGapOpen(ErrorMap err, IOBlastpConfig conf) {
    if (conf.getGapOpen() == null)
      return;

    if (conf.getTask() == IOBlastpTask.BLASTPFAST)
      err.putError(
        JsonKeys.GapOpen,
        String.format(BlastValidator.errNotTask, IOBlastpTask.BLASTPFAST.name)
      );
  }
}
