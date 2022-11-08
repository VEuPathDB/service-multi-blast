package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = GuestJobTransferRequestImpl.class
)
public interface GuestJobTransferRequest {
  @JsonProperty("guestID")
  Long getGuestID();

  @JsonProperty("guestID")
  void setGuestID(Long guestID);
}
