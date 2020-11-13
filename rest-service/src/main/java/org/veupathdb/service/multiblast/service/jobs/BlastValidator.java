package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

public class BlastValidator
{
  static final String
    ErrNoQuery = "is required.",
    ErrNoEmpty = "cannot be empty",
    ErrGt = "must be greater than %d",
    ErrGtEq = "must be greater than or equal to %d",
    ErrBetweenInc = "value must be between %d and %d (inclusive)",
    ErrIncompatibleWith = "is incompatible with field %s";

  private static BlastValidator instance;

  public ErrorMap validate(BlastConfig config, boolean ext) {
    var out = new ErrorMap();

    validateDbName(out, config, ext);
    validateQuery(out, config, ext);
    validateQueryLocation(out, config, ext);
    validateEntrezQuery(out, config, ext);
    validateExportSearchStrategy(out, config, ext);
    validateImportSearchStrategy(out, config, ext);
    validateLineLength(out, config, ext);
    validateMaxHSPs(out, config, ext);
    validateMaxTargetSeqs(out, config, ext);
    validateNumAlignments(out, config, ext);
    validateNumDescriptions(out, config, ext);
    validateNumThreads(out, config, ext);
    validateSearchSp(out, config, ext);
    validateWindowSize(out, config, ext);
    validateOutFormat(out, config, ext);
    validateQCovHspPerc(out, config, ext);

    return out;
  }

  public static BlastValidator getInstance() {
    if (instance == null)
      instance = new BlastValidator();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Helpers                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateDbName(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getDbName() == null)
      return;

    if (conf.getDbName().isBlank())
      err.putError(Util.key(ToolOption.BlastDatabase, ext), ErrNoEmpty);
  }

  static void validateQuery(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getQuery() == null)
      err.putError(Util.key(ToolOption.Query, ext), ErrNoQuery);
  }

  static final String ErrQueryLoc = "start position must be less than stop position";
  static void validateQueryLocation(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getQueryLoc() == null)
      return;

    if (conf.getQueryLoc().getStart() >= conf.getQueryLoc().getStop())
      err.putError(Util.key(ToolOption.QueryLocation, ext), ErrQueryLoc);
  }

  static void validateEntrezQuery(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getEntrezQuery() == null)
      return;

    if (!conf.getRemote())
      err.putError(Util.key(ToolOption.EntrezQuery, ext), "remote must be true to use this field");
  }

  static void validateExportSearchStrategy(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getExportSearchStrategy() == null)
      return;

    if (conf.getImportSearchStrategy() != null)
      err.putError(
        Util.key(ToolOption.ExportSearchStrategy, ext),
        String.format(ErrIncompatibleWith, ToolOption.ImportSearchStrategy)
      );
  }

  static void validateImportSearchStrategy(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getImportSearchStrategy() == null)
      return;

    if (conf.getExportSearchStrategy() != null)
      err.putError(
        Util.key(ToolOption.ImportSearchStrategy, ext),
        String.format(ErrIncompatibleWith, ToolOption.ExportSearchStrategy)
      );
  }

  static void validateLineLength(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getLineLength() == null)
      return;

    if (conf.getLineLength() < 1)
      err.putError(Util.key(ToolOption.LineLength, ext), String.format(ErrGtEq, 1));
  }

  static void validateMaxHSPs(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getMaxHSPs() == null)
      return;

    if (conf.getMaxHSPs() < 1)
      err.putError(Util.key(ToolOption.MaxHSPs, ext), String.format(ErrGtEq, 1));
  }

  static void validateMaxTargetSeqs(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getMaxTargetSequences() == null)
      return;

    if (conf.getMaxTargetSequences() < 1)
      err.putError(Util.key(ToolOption.MaxTargetSequences, ext), String.format(ErrGtEq, 1));

    if (conf.getNumAlignments() != null)
      err.putError(
        Util.key(ToolOption.MaxTargetSequences, ext),
        String.format(ErrIncompatibleWith, ToolOption.NumAlignments)
      );

    if (conf.getNumDescriptions() != null)
      err.putError(
        Util.key(ToolOption.MaxTargetSequences, ext),
        String.format(ErrIncompatibleWith, ToolOption.NumDescriptions)
      );
  }

  static void validateNumAlignments(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getNumAlignments() == null)
      return;

    if (conf.getNumAlignments() < 0)
      err.putError(Util.key(ToolOption.NumAlignments, ext), String.format(ErrGtEq, 0));

    if (conf.getMaxTargetSequences() != null)
      err.putError(
        Util.key(ToolOption.NumAlignments, ext),
        String.format(ErrIncompatibleWith, ToolOption.MaxTargetSequences)
      );
  }

  static void validateNumDescriptions(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getNumDescriptions() == null)
      return;

    if (conf.getNumDescriptions() < 0)
      err.putError(Util.key(ToolOption.NumDescriptions, ext), String.format(ErrGtEq, 0));

    if (conf.getMaxTargetSequences() != null)
      err.putError(
        Util.key(ToolOption.NumDescriptions, ext),
        String.format(ErrIncompatibleWith, ToolOption.MaxTargetSequences)
      );
  }

  static void validateNumThreads(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getNumThreads() == null)
      return;

    if (conf.getNumThreads() < 1)
      err.putError(Util.key(ToolOption.NumberOfThreads, ext), String.format(ErrGtEq, 1));

    if (conf.getRemote())
      err.putError(
        Util.key(ToolOption.NumberOfThreads, ext),
        String.format(ErrIncompatibleWith, ToolOption.Remote)
      );
  }

  static void validateSearchSp(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getSearchSpace() == null)
      return;

    if (conf.getSearchSpace() < 0)
      err.putError(Util.key(ToolOption.SearchSpaceEffectiveLength, ext), String.format(ErrGtEq, 0));
  }

  static void validateWindowSize(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getWindowSize() == null)
      return;

    if (conf.getWindowSize() < 0)
      err.putError(Util.key(ToolOption.MultiHitWindowSize, ext), String.format(ErrGtEq, 0));
  }

  static void validateOutFormat(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getOutFormat() == null)
      return;

    if (ext) {
      OutFormatValidator.validateExternal(conf.getOutFormat())
        .forEach((k, v) -> err.put(JsonKeys.OUT_FMT + "." + k, v));
    } else {
      OutFormatValidator.validateInternal(conf.getOutFormat())
        .forEach(s -> err.putError(ToolOption.OutputFormat, s));
    }
  }

  static void validateQCovHspPerc(ErrorMap err, BlastConfig conf, boolean ext) {
    if (conf.getQueryCoveragePercentHSP() == null)
      return;

    if (conf.getQueryCoveragePercentHSP() < 0 || conf.getQueryCoveragePercentHSP() > 100)
      err.putError(
        Util.key(ToolOption.QueryCoveragePercentHSP, ext),
        String.format(ErrBetweenInc, 0, 100)
      );
  }
}
