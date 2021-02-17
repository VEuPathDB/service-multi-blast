package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOLongJobResponseImpl.class)
public interface IOLongJobResponse extends IOShortJobResponse
{
  @JsonProperty(JsonKeys.Config)
  IOBlastConfig getConfig();

  @JsonProperty(JsonKeys.Config)
  IOLongJobResponse setConfig(IOBlastConfig config);
}
