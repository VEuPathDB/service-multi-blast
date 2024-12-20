package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Map;

@JsonDeserialize(
    as = BlastTargetMapImpl.class
)
public interface BlastTargetMap {
  @JsonAnyGetter
  Map<String, Object> getAdditionalProperties();

  @JsonAnySetter
  void setAdditionalProperties(String key, Object value);
}
