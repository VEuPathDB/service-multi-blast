package org.veupathdb.service.multiblast.service.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.InputBlastpConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastpTask;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastpTask;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

public class BlastPValidator extends BlastValidator
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

  public ErrorMap validateConfig(ErrorMap err, InputBlastpConfig conf) {
    log.trace("#validateConfig(ErrorMap, InputBlastpConfig)");

    optGtEq(err, conf.getWordSize(), 2, JsonKeys.WordSize);
    validateGapOpen(err, conf);
    validateGapExtend(err, conf);
    validateMatrix(err, conf);
    optGtEq(err, conf.getThreshold(), 0, JsonKeys.Threshold);
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

  private void validateBestHitScoreEdge(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  private void validateBestHitOverhang(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  private void validateCullingLimit(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getCullingLimit() == null) {
      gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  private void validateDbHardMask(ErrorMap err, InputBlastpConfig conf) {
    optIncompat(err, conf.getDbHardMask(), conf.getDbSoftMask(), DBHardMask, DBSoftMask);
    optIncompat(err, conf.getDbHardMask(), conf.getSubjectLoc(), DBHardMask, SubjectLocation);
  }

  private void validateDbSoftMask(ErrorMap err, InputBlastpConfig conf) {
    optIncompat(err, conf.getDbSoftMask(), conf.getDbHardMask(), DBSoftMask, DBHardMask);
    optIncompat(err, conf.getDbSoftMask(), conf.getSubjectLoc(), DBSoftMask, SubjectLocation);
  }

  private void validateNegativeTaxIds(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getNegativeTaxIds() == null || conf.getNegativeTaxIds().isEmpty())
      return;

    incompat(err, conf.getSubjectLoc(), NegativeTaxIDs, SubjectLocation);
  }

  private void validateTaxIds(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getTaxIds() == null || conf.getTaxIds().isEmpty())
      return;

    incompat(err, conf.getSubjectLoc(), TaxIDs, SubjectLocation);
  }

  private void validateSoftMasking(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getSoftMasking() == null || !conf.getSoftMasking())
      return;

    if (conf.getTask() != InputBlastpTask.BLASTP)
      err.putError(JsonKeys.SoftMasking, String.format(errOnlyTask, BlastpTask.BlastP));
  }

  private void validateSubjectLoc(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getSubjectLoc() != null) {
      colIncompat(err, conf.getTaxIds(), SubjectLocation, TaxIDs);
      colIncompat(err, conf.getNegativeTaxIds(), SubjectLocation, NegativeTaxIDs);
      incompat(err, conf.getDbSoftMask(), SubjectLocation, DBSoftMask);
      incompat(err, conf.getDbHardMask(), SubjectLocation, DBHardMask);
    }
  }

  private void validateMatrix(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getMatrix() == null)
      return;

    if (conf.getTask() == InputBlastpTask.BLASTPFAST)
      err.putError(JsonKeys.Matrix, String.format(errNotTask, BlastpTask.BlastPFast));
  }

  private void validateGapExtend(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getGapExtend() == null)
      return;

    if (conf.getTask() == InputBlastpTask.BLASTPFAST)
      err.putError(JsonKeys.GapExtend, String.format(errNotTask, BlastpTask.BlastPFast));
  }

  private void validateGapOpen(ErrorMap err, InputBlastpConfig conf) {
    if (conf.getGapOpen() == null)
      return;

    if (conf.getTask() == InputBlastpTask.BLASTPFAST)
      err.putError(JsonKeys.GapOpen, String.format(errNotTask, BlastpTask.BlastPFast));
  }
}
