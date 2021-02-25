package mb.lib.extern.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class JobCreateRequest
{
  private final String host;
  private final String[] payload;

  public JobCreateRequest(String host, String[] cli) {
    this.payload = cli;
    this.host = host;
  }

  @JsonGetter("url")
  public String getUrl() {
    return this.host;
  }

  @JsonGetter("payload")
  public String[] getPayload() {
    return payload;
  }
}
