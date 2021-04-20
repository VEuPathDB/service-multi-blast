package mb.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = IOJobTargetImpl.class)
public interface IOJobTarget
{
  @JsonGetter(JsonKeys.Organism)
  String organism();

  @JsonSetter(JsonKeys.Organism)
  IOJobTarget organism(String org);

  @JsonGetter(JsonKeys.Target)
  String target();

  @JsonSetter(JsonKeys.Target)
  IOJobTarget target(String tgt);
}
