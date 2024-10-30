package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "naTargets",
    "aaTargets"
})
public class BlastableTargetImpl implements BlastableTarget {
  @JsonProperty(
      value = "naTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  private List<String> naTargets;

  @JsonProperty(
      value = "aaTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  private List<String> aaTargets;

  @JsonProperty(
      value = "naTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  public List<String> getNaTargets() {
    return this.naTargets;
  }

  @JsonProperty(
      value = "naTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  public void setNaTargets(List<String> naTargets) {
    this.naTargets = naTargets;
  }

  @JsonProperty(
      value = "aaTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  public List<String> getAaTargets() {
    return this.aaTargets;
  }

  @JsonProperty(
      value = "aaTargets",
      defaultValue = "[\n"
              + "\n"
              + "]"
  )
  public void setAaTargets(List<String> aaTargets) {
    this.aaTargets = aaTargets;
  }
}
