package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(
    as = OutputSiteListingImpl.class
)
public interface OutputSiteListing {
  @JsonProperty("^[A-Za-z0-9_- ]+$")
  List<OutputListOrganism> getAZaZ0_9();

  @JsonProperty("^[A-Za-z0-9_- ]+$")
  void setAZaZ0_9(List<OutputListOrganism> aZaZ0_9);
}
