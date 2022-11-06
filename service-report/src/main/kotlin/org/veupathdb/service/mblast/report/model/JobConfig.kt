package org.veupathdb.service.mblast.report.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import org.gusdb.fgputil.Tuples.TwoTuple
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json

data class JobConfig(
  val queryID: HashID,
  val userAuth: TwoTuple<String, String>,
) {
  @JsonValue
  fun toJson() = Json.newObject {
    put("queryID", queryID.string)
    put("userAuth", Json.newObject {
      put("key", userAuth.first)
      put("value", userAuth.second)
    })
  }

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(json: ObjectNode) =
      JobConfig(
        HashID(json["queryID"].textValue()),
        json["userAuth"].let { TwoTuple(it["key"].textValue(), it["value"].textValue()) }
      )
  }
}
