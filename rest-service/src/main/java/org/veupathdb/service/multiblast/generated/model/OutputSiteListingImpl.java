package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("^[A-Za-z0-9_- ]+$")
public class OutputSiteListingImpl implements OutputSiteListing {
  @JsonProperty("^[A-Za-z0-9_- ]+$")
  private List<OutputListOrganism> aZaZ0_9;

  @JsonProperty("^[A-Za-z0-9_- ]+$")
  public List<OutputListOrganism> getAZaZ0_9() {
    return this.aZaZ0_9;
  }

  @JsonProperty("^[A-Za-z0-9_- ]+$")
  public void setAZaZ0_9(List<OutputListOrganism> aZaZ0_9) {
    this.aZaZ0_9 = aZaZ0_9;
  }
}
