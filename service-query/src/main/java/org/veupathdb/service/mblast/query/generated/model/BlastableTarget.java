package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(
    as = BlastableTargetImpl.class
)
public interface BlastableTarget {
  @JsonProperty(
      value = "naTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  List<String> getNaTargets();

  @JsonProperty(
      value = "naTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  void setNaTargets(List<String> naTargets);

  @JsonProperty(
      value = "aaTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  List<String> getAaTargets();

  @JsonProperty(
      value = "aaTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  void setAaTargets(List<String> aaTargets);
}
