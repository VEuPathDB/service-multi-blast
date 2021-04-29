package mb.api.model.blast;

import com.fasterxml.jackson.annotation.JsonProperty;
import mb.api.model.io.JsonKeys;

//@JsonDeserialize(as = IOBlastSegMaskImpl.class)
public interface IOBlastSegMask {
  @JsonProperty(JsonKeys.Window)
  Integer getWindow();

  @JsonProperty(JsonKeys.Window)
  void setWindow(Integer window);

  @JsonProperty(JsonKeys.LowCut)
   Double getLocut();

  @JsonProperty(JsonKeys.LowCut)
  void setLocut (Double locut);

  @JsonProperty(JsonKeys.HighCut)
   Double getHicut();

  @JsonProperty(JsonKeys.HighCut)
  void setHicut (Double hicut);
}
