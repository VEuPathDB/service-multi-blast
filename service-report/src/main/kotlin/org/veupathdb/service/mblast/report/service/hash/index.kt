package org.veupathdb.service.mblast.report.service.hash

import io.foxcapades.lib.md5.MD5
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json

fun HashConfig(config: BlastFormatter, queryJobID: HashID): HashID {
  return HashID(MD5.hash(Json.newObject {
    put("config",     config.toCliString())
    put("queryJobID", queryJobID.string)
  }.toString()).unsafeBytes)
}