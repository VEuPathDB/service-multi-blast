package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.generated.model.InputBlastnConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

public class BlastnValidator extends BlastValidator
{
  private static BlastnValidator instance;

  public ErrorMap validateConfig(ErrorMap errors, InputBlastnConfig config) {

    optGtEq(errors, config.getWordSize(), 4, JsonKeys.WordSize);
    optLtEq(errors, config.getPenalty(), 0, JsonKeys.Penalty);
    optGtEq(errors, config.getReward(), 0, JsonKeys.Reward);
    validateSubjectLoc(errors, config);
    validateTaxIds(errors, config);
    validateNegativeTaxIds(errors, config);
    validateDbSoftMask(errors, config);
    validateDbHardMask(errors, config);
    optBetweenInc(errors, config.getPercIdentity(), 0, 100, JsonKeys.PercentIdentity);
    optGtEq(errors, config.getCullingLimit(), 0, JsonKeys.CullingLimit);
    optBetweenExc(errors, config.getBestHitOverhang(), 0, 0.5, JsonKeys.BestHitOverhang);
    optBetweenExc(errors, config.getBestHitScoreEdge(), 0, 0.5, JsonKeys.BestHitScoreEdge);
    validateTemplateType(errors, config);
    validateTemplateLength(errors, config);

    return null;
  }

  public static BlastnValidator getInstance() {
    if (instance == null)
      instance = new BlastnValidator();

    return instance;
  }

  static void validateDbHardMask(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getDbHardMask() == null)
      return;

    if (conf.getDbSoftMask() != null)
      err.putError(JsonKeys.DBHardMask, String.format(ErrIncompatibleWith, JsonKeys.DBSoftMask));

    if (conf.getSubjectLoc() != null)
      err.putError(
        JsonKeys.DBHardMask,
        String.format(ErrIncompatibleWith, JsonKeys.SubjectLocation)
      );
  }

  static void validateDbSoftMask(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getDbSoftMask() == null)
      return;

    if (conf.getDbHardMask() != null)
      err.putError(JsonKeys.DBSoftMask, String.format(ErrIncompatibleWith, JsonKeys.DBHardMask));

    if (conf.getSubjectLoc() != null)
      err.putError(
        JsonKeys.DBSoftMask,
        String.format(ErrIncompatibleWith, JsonKeys.SubjectLocation)
      );
  }

  static void validateTaxIds(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getTaxIds() == null || conf.getTaxIds().isEmpty())
      return;

    if (conf.getNegativeTaxIds() != null && !conf.getNegativeTaxIds().isEmpty())
      err.putError(JsonKeys.TaxIDs, String.format(ErrIncompatibleWith, JsonKeys.NegativeTaxIDs));
    if (conf.getSubjectLoc() != null)
      err.putError(JsonKeys.TaxIDs, String.format(ErrIncompatibleWith, JsonKeys.SubjectLocation));
  }

  static void validateNegativeTaxIds(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getNegativeTaxIds() == null || conf.getNegativeTaxIds().isEmpty())
      return;

    if (conf.getTaxIds() != null && !conf.getTaxIds().isEmpty())
      err.putError(JsonKeys.NegativeTaxIDs, String.format(ErrIncompatibleWith, JsonKeys.TaxIDs));

    if (conf.getSubjectLoc() != null)
      err.putError(
        JsonKeys.NegativeTaxIDs,
        String.format(ErrIncompatibleWith, JsonKeys.SubjectLocation)
      );
  }

  static void validateSubjectLoc(ErrorMap err, InputBlastnConfig config) {
    if (config.getSubjectLoc() == null)
      return;

    if (config.getTaxIds() != null)
      err.putError(JsonKeys.SubjectLocation, String.format(ErrIncompatibleWith, JsonKeys.TaxIDs));

    if (config.getNegativeTaxIds() != null)
      err.putError(
        JsonKeys.SubjectLocation,
        String.format(ErrIncompatibleWith, JsonKeys.NegativeTaxIDs)
      );

    if (config.getDbSoftMask() != null)
      err.putError(
        JsonKeys.SubjectLocation,
        String.format(ErrIncompatibleWith, JsonKeys.DBSoftMask)
      );

    if (config.getDbHardMask() != null)
      err.putError(
        JsonKeys.SubjectLocation,
        String.format(ErrIncompatibleWith, JsonKeys.DBHardMask)
      );
  }

  static void validateWindowSize(ErrorMap err, InputBlastnConfig conf) {
    if (conf.getWindowSize() == null)
      return;

    if (conf.getWindowSize() < 0)
      err.putError(JsonKeys.MultiHitWindowSize, String.format(ErrGtEq, 0));
  }
}
