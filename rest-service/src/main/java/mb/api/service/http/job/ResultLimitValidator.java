package mb.api.service.http.job;

import java.util.Optional;

import mb.api.model.blast.IOBlastConfig;
import mb.api.model.io.JsonKeys;
import mb.api.service.model.ErrorMap;
import mb.lib.blast.model.ToolOption;

/**
 * {@code ResultLimitValidator} enforces that the given blast configuration
 * limits the result sequences at or below the given max result limit.
 *
 * Given the following:
 * <pre>
 * l = max results
 * q = number of query sequences
 * s = max_target_seqs value
 * d = num_descriptions value
 * a = num_alignments value
 * </pre>
 * The limit check is performed using one of the following checks:
 * <pre>
 * (q * s) <= l
 * OR
 * (q * max(d, a)) <= l
 * </pre>
 */
public class ResultLimitValidator
{
  private static ResultLimitValidator instance;

  private ResultLimitValidator() {}

  public static ResultLimitValidator getInstance() {
    if (instance == null)
      return instance = new ResultLimitValidator();

    return instance;
  }

  public static Optional<ErrorMap> validateResultLimit(int limit, int queries, IOBlastConfig conf) {
    return getInstance().validateResultLimits(limit, queries, conf);
  }

  public Optional<ErrorMap> validateResultLimits(int limit, int queries, IOBlastConfig conf) {
    var s = conf.getMaxTargetSeqs();
    var d = conf.getNumDescriptions();
    var a = conf.getNumAlignments();

    // If max_target_seqs (`s`) is set, then `d` and `a` must be null (according
    // to the blast config validation).
    if (s != null)
      return validateResultLimits(limit, queries, s);
    // If num_descriptions (`d`) is set (and optionally `a` is set)
    if (d != null)
      return validateResultLimits(limit, queries, d, a == null ? 0 : a);
    // If num_alignments (`a`) is set
    if (a != null)
      return validateResultLimits(limit, queries, 0, a);

    var err = "Must provide a max_target_seqs, num_alignments, or num_descriptions value to limit "
      + "results.";
    return Optional.of(new ErrorMap()
      .putError(JsonKeys.MaxResults, err)
      .putError(ToolOption.MaxTargetSequences, err)
      .putError(ToolOption.NumDescriptions, err)
      .putError(ToolOption.NumAlignments, err));
  }

  Optional<ErrorMap> validateResultLimits(int limit, int queries, long maxSeqs) {
    if (queries * maxSeqs > limit) {
      var err = "Number of query sequences * max_target_seqs cannot exceed " + limit;
      return Optional.of(new ErrorMap()
        .putError(JsonKeys.MaxResults, err)
        .putError(ToolOption.Query, err)
        .putError(ToolOption.MaxTargetSequences, err));
    }

    return Optional.empty();
  }

  Optional<ErrorMap> validateResultLimits(int limit, int queries, long descs, long aligns) {
    var max = Math.max(descs, aligns);

    if (queries * Math.max(descs, aligns) > limit) {
      var err = "Number of query sequences * "
        + (max == descs ? "num_descriptions" : "num_alignments")
        + " cannot exceed " + limit;
      return Optional.of(new ErrorMap()
        .putError(JsonKeys.MaxResults, err)
        .putError(ToolOption.Query, err)
        .putError((max == descs ? ToolOption.NumDescriptions : ToolOption.NumAlignments), err));
    }

    return Optional.empty();
  }
}
