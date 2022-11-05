package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID

data class JobCreateResponse(
  @JsonProperty("reportJobID")
  val reportJobID: HashID,
)
