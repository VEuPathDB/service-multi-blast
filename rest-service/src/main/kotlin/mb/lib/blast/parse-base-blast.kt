@file:Suppress("NOTHING_TO_INLINE")

package mb.lib.blast

import com.fasterxml.jackson.databind.node.NumericNode
import org.veupathdb.lib.blast.BlastBase
import org.veupathdb.lib.blast.CLIBase
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.Location

inline fun BlastBase.fromNumberJSON(k: String, j: NumericNode) = when (k) {
  Flag.DBSize                   -> dbSize                   = j.intValue().toByte()
  Flag.ExtensionDropoffUngapped -> extensionDropoffUngapped = j.doubleValue()
  Flag.QueryCoverageHSPPercent  -> queryCoverageHSPPercent  = j.doubleValue()
  Flag.MaxHSPs                  -> maxHSPs                  = j.longValue()
  Flag.SearchSpace              -> searchSpace              = j.shortValue()
  Flag.WindowSize               -> windowSize               = j.longValue()
  else                          -> (this as CLIBase).fromNumberJSON(k, j)
}

inline fun BlastBase.fromTextJSON(k: String, j: String) = when (k) {
  Flag.DBFile               -> dbFile               = j
  Flag.EntrezQuery          -> entrezQuery          = j
  Flag.ExpectValue          -> setExpectValue(j)
  Flag.ExportSearchStrategy -> exportSearchStrategy = j
  Flag.ImportSearchStrategy -> importSearchStrategy = j
  Flag.QueryFile            -> setQueryFile(j)
  Flag.QueryLocation        -> queryLocation        = Location.fromString(j)
  else                      -> (this as CLIBase).fromTextJSON(k, j)
}

inline fun BlastBase.fromBoolJSON(k: String, j: Boolean) = when (k) {
  Flag.LowercaseMasking -> lowercaseMasking = j
  Flag.SoftMasking      -> softMasking      = j
  Flag.Remote           -> remote           = j
  else                  -> (this as CLIBase).fromBoolJSON(k, j)
}