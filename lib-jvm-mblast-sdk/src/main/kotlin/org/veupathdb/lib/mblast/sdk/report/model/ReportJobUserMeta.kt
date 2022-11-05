package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ReportJobUserMeta(
  @JsonProperty("summary")
  val summary: String?,

  @JsonProperty("description")
  val description: String?,
)
