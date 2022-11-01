package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonProperty

data class BlastSeg(
  @JsonProperty("window") var window: Int?,
  @JsonProperty("locut")  var locut:  Double?,
  @JsonProperty("hicut")  var hicut:  Double?,
)