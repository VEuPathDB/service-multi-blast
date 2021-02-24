package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOLongJobResponseImpl extends IOShortJobResponseImpl implements IOLongJobResponse
{
  private IOBlastConfig config;

  public IOBlastConfig getConfig() {
    return this.config;
  }

  public IOLongJobResponse setConfig(IOBlastConfig config) {
    this.config = config;
    return this;
  }
}
