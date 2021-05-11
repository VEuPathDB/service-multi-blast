package mb.api.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("Site")
public class OutputSiteListingImpl implements OutputSiteListing {
  private List<OutputListOrganism> site;

  @JsonIgnore
  private final Map<String, Object> additionalProperties = new ExcludingMap();

  @JsonProperty("Site")
  public List<OutputListOrganism> getSite() {
    return this.site;
  }

  @JsonProperty("Site")
  public void setSite(List<OutputListOrganism> site) {
    this.site = site;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperties(String key, Object value) {
    this.additionalProperties.put(key, value);
  }
}
