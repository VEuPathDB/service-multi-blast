package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = IOJobTargetImpl.class)
public interface IOJobTarget
{
  @JsonGetter("organism")
  String organism();

  @JsonSetter("organism")
  IOJobTarget organism(String org);

  @JsonGetter("target")
  String target();

  @JsonSetter("target")
  IOJobTarget target(String tgt);
}
