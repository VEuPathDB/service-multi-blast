package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder
public class JobBulkStatusResponseImpl implements JobBulkStatusResponse {
  @JsonIgnore
  private Map<String, Object> additionalProperties = new ExcludingMap() {
     {
      addAcceptedPattern(Pattern.compile("^[\\dA-Fa-f]{32}$"));
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
