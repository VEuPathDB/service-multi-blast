package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("guestID")
public class GuestJobTransferRequestImpl implements GuestJobTransferRequest {
  @JsonProperty("guestID")
  private Long guestID;

  @JsonProperty("guestID")
  public Long getGuestID() {
    return this.guestID;
  }

  @JsonProperty("guestID")
  public void setGuestID(Long guestID) {
    this.guestID = guestID;
  }
}
