package org.veupathdb.service.multiblast.service.valid;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

public class BlastValidator implements ConfigValidator<IOBlastConfig>
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Error Messages                                                   ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public static final String
    errRequired         = "is required",
    ErrGenCode          = "must be equal to 33 or in one of the following inclusive ranges: [1..6], [9..16], [21-31]",
    ErrQueryLoc         = "start position must be less than stop position",
    errFmt0             = "only valid for the "
      + BlastReportType.Pairwise.getIoName()
      + " output format",
    errNotFmtGt4 = "only valid for the following output format types: "
      + Arrays.stream(BlastReportType.values())
      .limit(5)
      .map(BlastReportType::getIoName)
      .collect(Collectors.joining(", ")),
    errOnlyFmtGt4 = "not valid for the following output format types: "
      + Arrays.stream(BlastReportType.values())
      .limit(5)
      .map(BlastReportType::getIoName)
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

  private BlastValidator() {
    log.trace("BlastValidator#new()");
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

  @Override
  public ErrorMap validate(IOBlastConfig conf) {
    log.trace("BlastValidator#validate(IOBlastConfig)");

    var errors = new ErrorMap();

    validateQueryLocation(errors, conf);
    validateEValue(errors, conf);
    validateOutFormat(errors, conf);
    validateNumDescriptions(errors, conf);
    validateNumAlignments(errors, conf);
    Int.optGtEq(errors, conf.getLineLength(), 1, JsonKeys.LineLength);
    validateSortHits(errors, conf);
    validateSortHSPs(errors, conf);
    validateQCovHspPerc(errors, conf);
    Int.optGtEq(errors, conf.getMaxHSPs(), 1, JsonKeys.MaxHSPs);
    validateMaxTargetSeqs(errors, conf);
    Int.optGtEq(errors, conf.getSearchSpace(), (byte) 0, JsonKeys.SearchSpace);

    errors.putAll(switch (conf.getTool()) {
      case BLASTN -> BlastNValidator.getInstance().validate((IOBlastnConfig) conf);
      case BLASTP -> BlastPValidator.getInstance().validate((IOBlastpConfig) conf);
      case BLASTX -> BlastXValidator.getInstance().validate((IOBlastxConfig) conf);
      case TBLASTN -> TBlastNValidator.getInstance().validate((IOTBlastnConfig) conf);
      case TBLASTX -> TBlastXValidator.getInstance().validate((IOTBlastxConfig) conf);
    });

    return errors;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Methods                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃     Specialized Validations                                          ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateSortHits(ErrorMap err, IOBlastConfig conf) {
    if (conf.getSortHits() == null)
      return;

    if (conf.getOutFormat() == null || conf.getOutFormat().getFormat() == null)
      return;

    if (conf.getOutFormat().getFormat().ordinal() > 4)
      err.putError(JsonKeys.SortHits, errNotFmtGt4);
  }

  static void validateSortHSPs(ErrorMap err, IOBlastConfig conf) {
    if (conf.getSortHSPs() == null)
      return;

    if (conf.getOutFormat() == null || conf.getOutFormat().getFormat() == null)
      return;

    if (conf.getOutFormat().getFormat() != IOBlastFormat.PAIRWISE)
      err.putError(JsonKeys.SortHSPs, errFmt0);
  }

  static void validateQueryLocation(ErrorMap err, IOBlastConfig conf) {
    if (conf.getQueryLoc() == null)
      return;

    var ha = conf.getQueryLoc().getStart() == null;
    var ho = conf.getQueryLoc().getStop() == null;

    if (ha)
      err.putError(QueryLocation, Start, errRequired);
    if (ho)
      err.putError(QueryLocation, Stop, errRequired);

    if (!ha && !ho && conf.getQueryLoc().getStart() >= conf.getQueryLoc().getStop())
      err.putError(QueryLocation, ErrQueryLoc);
  }

  static final Pattern EValuePat = Pattern.compile("^\\d+(?:\\.\\d+)?(?:[eE]-?\\d+)?$");

  static void validateEValue(ErrorMap err, IOBlastConfig conf) {
    if (conf.getEValue() != null && !EValuePat.matcher(conf.getEValue()).matches())
      err.putError(JsonKeys.ExpectValue, errEValue);
  }

  static void validateOutFormat(ErrorMap err, IOBlastConfig conf) {
    if (conf.getOutFormat() == null)
      return;

    OutFormatValidator.validateExternal(conf.getOutFormat())
      .forEach((k, v) -> err.put(JsonKeys.OutFormat + "." + k, v));
  }

  static void validateNumDescriptions(ErrorMap err, IOBlastConfig conf) {
    if (conf.getNumDescriptions() == null)
      return;

    Int.gtEq(err, conf.getNumDescriptions(), 0, NumDescriptions);
    Obj.incompat(err, conf.getMaxTargetSeqs(), NumDescriptions, MaxTargetSequences);

    if (conf.getOutFormat() != null &&
      conf.getOutFormat().getFormat() != null &&
      conf.getOutFormat().getFormat().ordinal() > 4
    )
      err.putError(JsonKeys.NumDescriptions, errNotFmtGt4);
  }

  static void validateNumAlignments(ErrorMap err, IOBlastConfig conf) {
    if (conf.getNumAlignments() != null) {
      Int.gtEq(err, conf.getNumAlignments(), 0, NumAlignments);
      Obj.incompat(err, conf.getMaxTargetSeqs(), NumAlignments, MaxTargetSequences);
    }
  }

  static void validateQCovHspPerc(ErrorMap err, IOBlastConfig conf) {
    if (conf.getQCovHSPPerc() != null)
      Dec.betweenInc(err, conf.getQCovHSPPerc(), 0, 100, QueryCoverageHSPPercent);
  }

  static void validateMaxTargetSeqs(ErrorMap err, IOBlastConfig conf) {
    if (conf.getMaxTargetSeqs() == null)
      return;

    Int.gtEq(err, conf.getMaxTargetSeqs(), 1, MaxTargetSequences);
    Obj.incompat(err, conf.getNumAlignments(), MaxTargetSequences, NumAlignments);
    Obj.incompat(err, conf.getNumDescriptions(), MaxTargetSequences, NumDescriptions);

    if (conf.getOutFormat() == null
      || conf.getOutFormat().getFormat() == null
      || conf.getOutFormat().getFormat().ordinal() < 5
    )
      err.putError(MaxTargetSequences, errOnlyFmtGt4);
  }

  static void validateGenCode(ErrorMap err, Byte gc, String field) {
    if (gc == null)
      return;

    if (gc < 1 || gc > 33 || (gc > 6 && gc < 9) || (gc > 16 && gc < 21) || gc == 32)
      err.putError(field, ErrGenCode);
  }

}
