package mb.lib.formatter.model;

import mb.lib.queue.model.CreateRequest;

public class Request extends CreateRequest<Payload>
{
  public Request(String host, Payload payload) {
    super(host, payload);
  }
}
