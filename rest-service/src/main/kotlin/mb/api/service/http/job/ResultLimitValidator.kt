package mb.api.service.http.job

import mb.api.model.blast.IOBlastConfig
import mb.api.model.dmnd.IODiamondConfig
import mb.api.model.io.JsonKeys
import mb.api.service.model.ErrorMap
import mb.lib.blast.model.ToolOption
import org.apache.logging.log4j.LogManager
import kotlin.math.max

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
object ResultLimitValidator {
  private val Log = LogManager.getLogger("ResultLimitValidator")

  fun validateResultLimits(limit: Int, queries: Int, conf: IOBlastConfig): ErrorMap? {
    Log.trace("validateResultLimits(limit = {}, queries = {}, conf = ...)", limit, queries)

    val s = conf.maxTargetSeqs
    val d = conf.numDescriptions
    val a = conf.numAlignments

    // If max_target_seqs (`s`) is set, then `d` and `a` must be null (according
    // to the blast config validation).
    if (s != null)
      return validateResultLimits(limit, queries, s)
    // If num_descriptions (`d`) is set (and optionally `a` is set)
    if (d != null)
      return validateResultLimits(limit, queries, d, a ?: 0)
    // If num_alignments (`a`) is set
    if (a != null)
      return validateResultLimits(limit, queries, 0, a)

    val err = "Must provide a max_target_seqs, num_alignments, or num_descriptions value to limit results."
    return ErrorMap()
      .putError(JsonKeys.MaxResults, err)
      .putError(ToolOption.MaxTargetSequences, err)
      .putError(ToolOption.NumDescriptions, err)
      .putError(ToolOption.NumAlignments, err)
  }

  fun validateResultLimits(limit: Int, queries: Int, conf: IODiamondConfig): ErrorMap? =
    when (val mts = conf.maxTargetSeqs) {
      null -> ErrorMap().putError(JsonKeys.MaxResults, "Must provide max_target_seqs value to limit results.")
      else -> validateResultLimits(limit, queries, mts)
    }

  fun validateResultLimits(limit: Int, queries: Int, maxSeqs: Long): ErrorMap? {
    Log.trace("validateResultLimits(limit = {}, queries = {}, maxSeqs = {})", limit, queries, maxSeqs)

    if (queries * maxSeqs > limit) {
      val err = "Number of query sequences * max_target_seqs cannot exceed $limit"
      return ErrorMap()
        .putError(JsonKeys.MaxResults, err)
        .putError(ToolOption.Query, err)
        .putError(ToolOption.MaxTargetSequences, err)
    }

    return null
  }

  fun validateResultLimits(limit: Int, queries: Int, descs: Long, aligns: Long): ErrorMap? {
    Log.trace("validateResultLimits(limit = {}, queries = {}, descs = {}, aligns = {})", limit, queries, descs, aligns)

    val max = max(descs, aligns)

    if (queries * max(descs, aligns) > limit) {
      val err = "Number of query sequences * ${if (max == descs) "num_descriptions" else "num_alignments"} cannot " +
                "exceed $limit"

      return ErrorMap()
        .putError(JsonKeys.MaxResults, err)
        .putError(ToolOption.Query, err)
        .putError(if(max == descs) ToolOption.NumDescriptions else ToolOption.NumAlignments, err)
    }

    return null
  }
}
