package mb.api.model.blast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.blast.impl.IOBlastLocationImpl;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = IOBlastLocationImpl.class)
public interface IOBlastLocation {
  @JsonProperty(JsonKeys.Start)
   Long getStart();

  @JsonProperty(JsonKeys.Start)
  void setStart (Long start);

  @JsonProperty(JsonKeys.Stop)
   Long getStop();

  @JsonProperty(JsonKeys.Stop)
  void setStop (Long stop);
}
