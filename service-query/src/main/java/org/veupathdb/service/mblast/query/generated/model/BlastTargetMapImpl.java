package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Map;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder
public class BlastTargetMapImpl implements BlastTargetMap {
  @JsonIgnore
  private Map<String, Object> additionalProperties = new ExcludingMap() {
     {
      addAcceptedPattern(Pattern.compile("^\\w+$"));
    }
  }
  ;

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperties(String key, Object value) {
    this.additionalProperties.put(key, value);
  }
}
