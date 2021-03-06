package mb.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = MetaResponseImpl.class)
public interface MetaResponse {
  @JsonProperty(JsonKeys.Targets)
  OutputSiteListing getTargets();

  @JsonProperty(JsonKeys.Targets)
  void setTargets(OutputSiteListing targets);
}
