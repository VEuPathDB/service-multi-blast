package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty

data class FileEntry(
  @JsonProperty("name")
  val name: String,

  @JsonProperty("size")
  val size: Long,
)
