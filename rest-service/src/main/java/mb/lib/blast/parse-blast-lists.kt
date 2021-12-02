@file:Suppress("NOTHING_TO_INLINE")

package mb.lib.blast

import com.fasterxml.jackson.databind.node.NumericNode
import org.veupathdb.lib.blast.BlastBase
import org.veupathdb.lib.blast.BlastWithIPGList
import org.veupathdb.lib.blast.BlastWithLists
import org.veupathdb.lib.blast.consts.Flag

val ListSplit = Regex(" *, *")

inline fun BlastWithLists.fromBoolJSON(key: String, value: Boolean) = (this as BlastBase).fromBoolJSON(key, value)

inline fun BlastWithLists.fromTextJSON(key: String, value: String) = when(key) {
  Flag.GIList                 -> giList                 = value
  Flag.NegativeGIList         -> negativeGIList         = value
  Flag.NegativeSequenceIDList -> negativeSequenceIDList = value
  Flag.NegativeTaxIDs         -> negativeTaxIDs         = value.split(ListSplit, -1).toList()
  Flag.NegativeTaxIDList      -> negativeTaxIDList      = value
  Flag.SequenceIDList         -> sequenceIDList         = value
  Flag.TaxIDs                 -> taxIDs                 = value.split(ListSplit, -1).toList()
  Flag.TaxIDList              -> taxIDList              = value
  else                        -> (this as BlastBase).fromTextJSON(key, value)
}

inline fun BlastWithLists.fromNumberJSON(key: String, value: NumericNode) = (this as BlastBase).fromNumberJSON(key, value)

//
//
//

inline fun BlastWithIPGList.fromBoolJSON(key: String, value: Boolean) = (this as BlastWithLists).fromBoolJSON(key, value)

inline fun BlastWithIPGList.fromTextJSON(key: String, value: String) = when(key) {
  Flag.IPGList         -> ipgList         = value
  Flag.NegativeIPGList -> negativeIPGList = value
  else                 -> (this as BlastWithLists).fromTextJSON(key, value)
}

inline fun BlastWithIPGList.fromNumberJSON(key: String, value: NumericNode) = (this as BlastWithLists).fromNumberJSON(key, value)

