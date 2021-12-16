@file:Suppress("NOTHING_TO_INLINE")

package mb.lib.blast

import com.fasterxml.jackson.databind.node.NumericNode
import org.veupathdb.lib.blast.CLIBase
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.*

inline fun CLIBase.fromBoolJSON(key: String, value: Boolean) = when (key) {
  Flag.ParseDefLines -> parseDefLines = value
  Flag.ShowGIs       -> showGIs       = value
  Flag.HTML          -> html          = value
  Flag.ShortHelp     -> shortHelp     = value
  Flag.LongHelp      -> longHelp      = value
  Flag.Version       -> version       = value
  else               -> { /* do nothing */ }
}

inline fun CLIBase.fromNumberJSON(key: String, value: NumericNode) = when (key) {
  Flag.NumDescriptions    -> numDescriptions = NumDescriptions(value.longValue())
  Flag.NumAlignments      -> numAlignments = NumAlignments(value.longValue())
  Flag.LineLength         -> lineLength = LineLength(value.intValue())
  Flag.MaxTargetSequences -> maxTargetSequences = MaxTargetSeqs(value.longValue())
  Flag.SortHits           -> sortHits = HitSorting.fromIntValue(value.intValue())
  Flag.SortHSPs           -> sortHSPs = HSPSorting.fromIntValue(value.intValue())
  else                    -> { /* do nothing */ }
}

inline fun CLIBase.fromTextJSON(k: String, value: String) = when (k) {
  Flag.OutFormat -> outFormat = OutFormat.fromString(value)
  Flag.OutFile   -> outFile = OutFile(value)
  else           -> { /* do nothing */ }
}
