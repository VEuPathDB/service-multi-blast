package mb.lib.queue.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class CreateRequest <T>
{
  private final String host;
  private final T payload;

  public CreateRequest(String host, T payload) {
    this.host    = host;
    this.payload = payload;
  }

  @JsonGetter("url")
  public String getHost() {
    return host;
  }

  @JsonGetter("payload")
  public T getPayload() {
    return payload;
  }
}
