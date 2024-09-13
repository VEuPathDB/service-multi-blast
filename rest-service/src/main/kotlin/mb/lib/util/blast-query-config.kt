package mb.lib.util

import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.blast.BlastQueryConfig
import org.veupathdb.lib.blast.consts.Flag

fun BlastQueryConfig.toHashable(): JsonNode {
  val tmp = toJSON().toJSON()

  tmp.remove(Flag.OutFormat)

  return tmp
}

