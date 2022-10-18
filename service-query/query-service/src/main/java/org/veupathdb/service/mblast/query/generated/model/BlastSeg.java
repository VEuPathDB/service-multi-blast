package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = BlastSegImpl.class
)
public interface BlastSeg {
  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  Integer getWindow();

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  void setWindow(Integer window);

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  Double getLocut();

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  void setLocut(Double locut);

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  Double getHicut();

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  void setHicut(Double hicut);
}
