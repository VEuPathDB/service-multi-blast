package mb.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = IOGuestLinkImpl.class)
public interface IOGuestLink
{
  @JsonProperty(JsonKeys.GuestID)
  long getGuestID();

  @JsonProperty(JsonKeys.GuestID)
  void setGuestID(long guestID);
}
