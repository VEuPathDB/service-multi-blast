package org.veupathdb.service.multiblast.service.jobs;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.InputBlastConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastFormat;
import org.veupathdb.service.multiblast.generated.model.InputBlastnConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastpConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.ReportFormatType;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

public class BlastValidator
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Error Messages                                                   ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  protected static final String
    ErrRequired         = "is required.",
    ErrNoEmpty          = "cannot be empty",
    ErrGt               = "must be greater than %d",
    errGtEq             = "must be greater than or equal to %d",
    ErrLtEq             = "must be less than or equal to %d",
    ErrBetweenInc       = "value must be between %d and %d (inclusive)",
    ErrBetweenIncF      = "value must be between %.1f and %.1f (inclusive)",
    ErrBetweenExcF      = "value must be between %.1f and %.1f (exclusive)",
    errIncompatibleWith = "is incompatible with field %s",
    ErrGenCode          = "must be equal to 33 or in one of the following inclusive ranges: [1..6], [9..16], [21-31]",
    ErrQueryLoc         = "start position must be less than stop position",
    errFmt0             = "only valid for the "
      + ReportFormatType.Pairwise.getIoName()
      + " output format",
    errNotFmtGt4        = "only valid for the following output format types: "
      + Arrays.stream(ReportFormatType.values())
      .limit(5)
      .map(ReportFormatType::getIoName)
      .collect(Collectors.joining(", ")),
    errOnlyFmtGt4       = "not valid for the following output format types: "
      + Arrays.stream(ReportFormatType.values())
      .limit(5)
      .map(ReportFormatType::getIoName)
      .collect(Collectors.joining(", ")),
    errEValue           = "must be a decimal value (optionally in E notation).",
    errOnlyTask         = "can only be used with task type %s",
    errNotTask          = "cannot be used with task type %s";

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Management                                              ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static BlastValidator instance;

  private final Logger log = LogManager.getLogger(getClass());

  protected BlastValidator() {
  }

  public static BlastValidator getInstance() {
    if (instance == null)
      instance = new BlastValidator();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Public API                                                       ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public ErrorMap validate(InputBlastConfig conf) {
    var errors = new ErrorMap();

    validateQueryLocation(errors, conf);
    validateEValue(errors, conf);
    validateOutFormat(errors, conf);
    validateNumDescriptions(errors, conf);
    validateNumAlignments(errors, conf);
    optGtEq(errors, conf.getLineLength(), 1, JsonKeys.LineLength);
    validateSortHits(errors, conf);
    validateSortHSPs(errors, conf);
    validateQCovHspPerc(errors, conf);
    optGtEq(errors, conf.getMaxHSPs(), 1, JsonKeys.MaxHSPs);
    validateMaxTargetSeqs(errors, conf);
    optGtEq(errors, conf.getSearchSpace(), 0, JsonKeys.SearchSpace);

    return switch (conf.getTool()) {
      case BLASTN -> BlastNValidator.getInstance().validateConfig(errors, (InputBlastnConfig) conf);
      case BLASTP -> BlastPValidator.getInstance().validateConfig(errors, (InputBlastpConfig) conf);
      case BLASTX -> null;
      case TBLASTN -> null;
      case TBLASTX -> null;
    };
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Methods                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃     General Purpose Validations                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  /**
   * Optional Incompatibility Check
   * <p>
   * If {@code targ} is null, does nothing.<br>
   * If {@code targ} is not null and {@code other} is null, does nothing.<br>
   * If {@code targ} is not null and {@code other} is not null, appends an error
   * keyed on {@code tField} for incompatibility with {@code oField}.
   *
   * @param err Error collection
   * @param targ Target/current object
   * @param other Object that {@code targ} is incompatible with.
   * @param tField Target/current field name.
   * @param oField Field name for {@code other}
   */
  static void optIncompat(ErrorMap err, Object targ, Object other, String tField, String oField) {
    if (targ == null)
      return;

    incompat(err, other, tField, oField);
  }

  /**
   * Incompatibility Check
   * <p>
   * If {@code other} is null, does nothing.<br>
   * If {@code other} is not null, appends an error keyed on {@code tField} for
   * incompatibility with {@code oField}.
   *
   * @param err Error collection
   * @param other Object that the calling context is incompatible with.
   * @param tField Target/current field name.
   * @param oField Field name for {@code other}
   */
  static void incompat(ErrorMap err, Object other, String tField, String oField) {
    if (other != null)
      err.putError(tField, String.format(errIncompatibleWith, oField));
  }

  /**
   * Collection Incompatibility Check
   * <p>
   * If {@code other} is null, does nothing.<br>
   * If {@code other} is not null and is empty, does nothing.<br>
   * If {@code other} is not null and is not empty, appends an error keyed on
   * {@code tField} for incompatibility with {@code oField}.
   *
   * @param err Error collection
   * @param other Collection that the calling context is incompatible with.
   * @param tField Target/current field name.
   * @param oField Field name for {@code other}
   */
  static void colIncompat(ErrorMap err, Collection<?> other, String tField, String oField) {
    if (other != null && !other.isEmpty())
      err.putError(tField, String.format(errIncompatibleWith, oField));
  }

  static void optLtEq(ErrorMap err, Integer val, int max, String field) {
    if (val == null)
      return;

    if (val > max)
      err.putError(field, String.format(ErrLtEq, max));
  }

  static void optGtEq(ErrorMap err, Double val, double min, String field) {
    if (val == null)
      return;

    if (val < min)
      err.putError(field, String.format(errGtEq, min));
  }

  static void gtEq(ErrorMap err, Integer val, int min, String field) {
    if (val < min)
      err.putError(field, String.format(errGtEq, min));
  }

  static void optGtEq(ErrorMap err, Integer val, int min, String field) {
    if (val != null)
      gtEq(err, val, min, field);
  }

  static void optGtEq(ErrorMap err, Short val, int min, String field) {
    if (val == null)
      return;

    if (val < min)
      err.putError(field, String.format(errGtEq, min));
  }

  static void optGtEq(ErrorMap err, Byte val, int min, String field) {
    if (val == null)
      return;

    if (val < min)
      err.putError(field, String.format(errGtEq, min));
  }

  static void optBetweenInc(ErrorMap err, Double val, double min, double max, String field) {
    if (val == null)
      return;

    if (val < min || val > max)
      err.putError(field, String.format(ErrBetweenIncF, min, max));
  }

  static void betweenExc(ErrorMap err, Double val, double min, double max, String field) {
    if (val <= min || val >= max)
      err.putError(field, String.format(ErrBetweenExcF, min, max));
  }

  static void optBetweenExc(ErrorMap err, Double val, double min, double max, String field) {
    if (val != null)
      betweenExc(err, val, min, max, field);
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃     Specialized Validations                                          ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateSortHits(ErrorMap err, InputBlastConfig conf) {
    if (conf.getSortHits() == null)
      return;

    if (conf.getOutFormat() == null || conf.getOutFormat().getFormat() == null)
      return;

    if (conf.getOutFormat().getFormat().ordinal() > 4)
      err.putError(JsonKeys.SortHits, errNotFmtGt4);
  }

  static void validateSortHSPs(ErrorMap err, InputBlastConfig conf) {
    if (conf.getSortHits() == null)
      return;

    if (conf.getOutFormat() == null || conf.getOutFormat().getFormat() == null)
      return;

    if (conf.getOutFormat().getFormat() != InputBlastFormat.PAIRWISE)
      err.putError(JsonKeys.SortHSPs, errFmt0);
  }

  static void validateQueryLocation(ErrorMap err, InputBlastConfig conf) {
    if (conf.getQueryLoc() == null)
      return;

    if (conf.getQueryLoc().getStart() >= conf.getQueryLoc().getStop())
      err.putError(JsonKeys.QueryLocation, ErrQueryLoc);
  }

  static final Pattern EValuePat = Pattern.compile("^\\d+(?:\\.\\d+)?(?:[eE]-?\\d+)?$");

  static void validateEValue(ErrorMap err, InputBlastConfig conf) {
    if (!EValuePat.matcher(conf.getEValue()).matches())
      err.putError(JsonKeys.ExpectValue, errEValue);
  }

  static void validateOutFormat(ErrorMap err, InputBlastConfig conf) {
    if (conf.getOutFormat() == null)
      return;

    OutFormatValidator.validateExternal(conf.getOutFormat())
      .forEach((k, v) -> err.put(JsonKeys.OUT_FMT + "." + k, v));
  }

  static void validateNumDescriptions(ErrorMap err, InputBlastConfig conf) {
    if (conf.getNumDescriptions() == null)
      return;

    if (conf.getNumDescriptions() < 0)
      err.putError(JsonKeys.NumDescriptions, String.format(errGtEq, 0));

    if (conf.getOutFormat() != null &&
      conf.getOutFormat().getFormat() != null &&
      conf.getOutFormat().getFormat().ordinal() > 4
    )
      err.putError(JsonKeys.NumDescriptions, errNotFmtGt4);

    if (conf.getMaxTargetSeqs() != null)
      err.putError(
        JsonKeys.NumDescriptions,
        String.format(errIncompatibleWith, ToolOption.MaxTargetSequences)
      );
  }

  static void validateNumAlignments(ErrorMap err, InputBlastConfig conf) {
    if (conf.getNumAlignments() == null)
      return;

    if (conf.getNumAlignments() < 0)
      err.putError(JsonKeys.NumAlignments, String.format(errGtEq, 0));

    if (conf.getMaxTargetSeqs() != null)
      err.putError(
        JsonKeys.NumAlignments,
        String.format(errIncompatibleWith, ToolOption.MaxTargetSequences)
      );
  }

  static void validateQCovHspPerc(ErrorMap err, InputBlastConfig conf) {
    if (conf.getQCovHSPPerc() == null)
      return;

    if (conf.getQCovHSPPerc() < 0 || conf.getQCovHSPPerc() > 100)
      err.putError(JsonKeys.QuerytCoverageHSPPercent, String.format(ErrBetweenInc, 0, 100));
  }

  static void validateMaxTargetSeqs(ErrorMap err, InputBlastConfig conf) {
    if (conf.getMaxTargetSeqs() == null)
      return;

    if (conf.getMaxTargetSeqs() < 1)
      err.putError(JsonKeys.MaxTargetSequences, String.format(errGtEq, 1));

    if (conf.getNumAlignments() != null)
      err.putError(
        JsonKeys.MaxTargetSequences,
        String.format(errIncompatibleWith, ToolOption.NumAlignments)
      );

    if (conf.getNumDescriptions() != null)
      err.putError(
        JsonKeys.MaxTargetSequences,
        String.format(errIncompatibleWith, ToolOption.NumDescriptions)
      );

    if (conf.getOutFormat() == null
      || conf.getOutFormat().getFormat() == null
      || conf.getOutFormat().getFormat().ordinal() < 5
    )
      err.putError(JsonKeys.MaxTargetSequences, errOnlyFmtGt4);

  }

  static void validateGenCode(ErrorMap err, Integer gc, String field) {
    if (gc == null)
      return;

    if (gc < 1 || gc > 33 || (gc > 6 && gc < 9) || (gc > 16 && gc < 21) || gc == 32)
      err.putError(field, ErrGenCode);
  }

}
